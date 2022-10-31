package com.example.dragonballapp.data.repository

import androidx.paging.PagingData
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.repository.DataStoreOperation
import com.example.dragonballapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStoreOperation: DataStoreOperation,
    private val localDataSource: LocalDataSource
) {


    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteDataSource.searchHeroes(query)
    }

    suspend fun getSelectedHeroes(heroId: Int): Hero {
        return localDataSource.getSelectedHero(heroId = heroId)
    }


    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperation.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperation.readOnBoardingState()
    }

}