package com.example.dragonballapp.domain.use_cases.selectedhero_usecase

import com.example.dragonballapp.data.repository.Repository
import com.example.dragonballapp.domain.model.Hero

class SelectedHeroUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHeroes(heroId = heroId)
    }
}