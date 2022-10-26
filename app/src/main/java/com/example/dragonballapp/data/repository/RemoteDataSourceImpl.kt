package com.example.dragonballapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dragonballapp.data.local.DragonballDatabase
import com.example.dragonballapp.data.paging_source.HeroRemoteMediator
import com.example.dragonballapp.data.paging_source.SearchHeroSource
import com.example.dragonballapp.data.remote.DragonballApi
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.repository.RemoteDataSource
import com.example.dragonballapp.util.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val dragonballApi: DragonballApi,
    private val dragonballDatabase: DragonballDatabase
) : RemoteDataSource {

    private val heroDao = dragonballDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                dragonballApi = dragonballApi,
                dragonballDatabase = dragonballDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE), pagingSourceFactory = {
                SearchHeroSource(dragonballApi = dragonballApi, query = query)
            }
        ).flow
    }


}