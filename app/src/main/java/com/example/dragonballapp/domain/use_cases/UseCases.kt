package com.example.dragonballapp.domain.use_cases

import com.example.dragonballapp.domain.use_cases.allheroes_usecase.GetAllHeroesUseCase
import com.example.dragonballapp.domain.use_cases.readonboarding_usecase.ReadOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.saveonboarding_usecase.SaveOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.search_heroes_usecase.SearchHeroesUseCase
import com.example.dragonballapp.domain.use_cases.selectedhero_usecase.SelectedHeroUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val selectedHeroUseCase: SelectedHeroUseCase
)