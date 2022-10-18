package com.example.dragonballapp.di

import android.content.Context
import androidx.annotation.RequiresPermission.Read
import com.example.dragonballapp.data.pref.DataStoreOperationImpl
import com.example.dragonballapp.data.pref.Repository
import com.example.dragonballapp.domain.repository.DataStoreOperation
import com.example.dragonballapp.domain.use_cases.UseCases
import com.example.dragonballapp.domain.use_cases.readonboarding_usecase.ReadOnBoardingUseCase
import com.example.dragonballapp.domain.use_cases.saveonboarding_usecase.SaveOnBoardingUseCase
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
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }

}