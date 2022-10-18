package com.example.dragonballapp.domain.use_cases

import com.example.dragonballapp.domain.use_cases.readonboarding_usecase.ReadOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.saveonboarding_usecase.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)