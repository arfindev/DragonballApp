package com.example.dragonballapp.domain.use_cases.search_heroes_usecase

import androidx.paging.PagingData
import com.example.dragonballapp.data.repository.Repository
import com.example.dragonballapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query)
    }

}