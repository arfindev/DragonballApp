package com.example.dragonballapp.domain.use_cases.readonboarding_usecase

import com.example.dragonballapp.data.pref.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()

    }

}