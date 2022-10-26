package com.example.dragonballapp.di

import android.content.Context
import com.example.dragonballapp.data.repository.DataStoreOperationImpl
import com.example.dragonballapp.data.repository.Repository
import com.example.dragonballapp.domain.repository.DataStoreOperation
import com.example.dragonballapp.domain.use_cases.UseCases
import com.example.dragonballapp.domain.use_cases.allheroes_usecase.GetAllHeroesUseCase
import com.example.dragonballapp.domain.use_cases.readonboarding_usecase.ReadOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.saveonboarding_usecase.SaveOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.search_heroes_usecase.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperation(@ApplicationContext context: Context): DataStoreOperation {
        return DataStoreOperationImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )
    }

}