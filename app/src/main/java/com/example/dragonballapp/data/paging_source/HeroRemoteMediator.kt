package com.example.dragonballapp.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.dragonballapp.data.local.DragonballDatabase
import com.example.dragonballapp.data.remote.DragonballApi
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.model.HeroRemoteKeys
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val dragonballApi: DragonballApi,
    private val dragonballDatabase: DragonballDatabase
) : RemoteMediator<Int, Hero>() {
    private val heroDao = dragonballDatabase.heroDao()
    private val heroRemoteKeysDao = dragonballDatabase.heroRemoteKeydao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKey(heroId = 1)?.lastUpdated ?: 0L
        val cacheTimeOut = 1440
        Log.d("RemoteMediator", "Current TIme: ${parseMillis(currentTime)} ")
        Log.d("RemoteMediator", "Last Updated Time: ${parseMillis(lastUpdated)} ")

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeOut) {
            Log.d("RemoteMediator", "Up to date  ")
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            Log.d("RemoteMediator", "Refresh  ")

            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = dragonballApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                dragonballDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKey(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKey(heroId = hero.id)

        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKey(heroId = hero.id)

        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKey(heroId = id)

            }
        }
    }

    private fun parseMillis(millis: Long): String {
        val date = Date(millis)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
}