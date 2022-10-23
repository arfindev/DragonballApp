package com.example.dragonballapp.di

import com.example.dragonballapp.data.local.DragonballDatabase
import com.example.dragonballapp.data.remote.DragonballApi
import com.example.dragonballapp.data.repository.RemoteDataSourceImpl
import com.example.dragonballapp.domain.repository.RemoteDataSource
import com.example.dragonballapp.util.Constant.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpsClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES).build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }


    @Provides
    @Singleton
    fun provideDragonballApi(retrofit: Retrofit): DragonballApi {
        return retrofit.create(DragonballApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSourceImpl(
        dragonballApi: DragonballApi,
        dragonballDatabase: DragonballDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            dragonballApi = dragonballApi,
            dragonballDatabase = dragonballDatabase

        )
    }


}