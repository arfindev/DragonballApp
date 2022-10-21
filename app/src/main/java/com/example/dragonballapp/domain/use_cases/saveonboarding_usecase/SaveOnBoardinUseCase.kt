package com.example.dragonballapp.domain.use_cases.saveonboarding_usecase

import com.example.dragonballapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)

    }
}