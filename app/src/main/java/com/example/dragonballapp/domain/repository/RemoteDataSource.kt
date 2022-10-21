package com.example.dragonballapp.domain.repository

import androidx.paging.PagingData
import com.example.dragonballapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>

}