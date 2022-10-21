package com.example.dragonballapp.domain.use_cases.allheroes_usecase

import androidx.paging.PagingData
import com.example.dragonballapp.data.repository.Repository
import com.example.dragonballapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}