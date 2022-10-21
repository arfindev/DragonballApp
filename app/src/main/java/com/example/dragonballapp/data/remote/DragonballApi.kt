package com.example.dragonballapp.data.remote

import com.example.dragonballapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonballApi {

    @GET("/dragonball/heroes")
    suspend fun getAllHeroes(
        @Query("page") page : Int = 1
    ): ApiResponse

    @GET("/dragonball/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name : String
    ): ApiResponse

}