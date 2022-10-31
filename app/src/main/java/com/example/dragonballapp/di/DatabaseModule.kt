package com.example.dragonballapp.di

import android.content.Context
import androidx.room.Room
import com.example.dragonballapp.data.local.DragonballDatabase
import com.example.dragonballapp.data.repository.LocalDataSource
import com.example.dragonballapp.data.repository.LocalDataSourceImpl
import com.example.dragonballapp.util.Constant.DRAGON_BALL_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DragonballDatabase {
        return Room.databaseBuilder(
            context,
            DragonballDatabase::class.java,
            DRAGON_BALL_DATABASE
        ).build()

    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dragonballDatabase: DragonballDatabase): LocalDataSource {
        return LocalDataSourceImpl(dragonballDatabase = dragonballDatabase)
    }


}