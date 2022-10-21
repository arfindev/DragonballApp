package com.example.dragonballapp.data.repository

import androidx.paging.PagingData
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.repository.DataStoreOperation
import com.example.dragonballapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStoreOperation: DataStoreOperation
) {


    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getAllHeroes()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperation.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperation.readOnBoardingState()
    }

}