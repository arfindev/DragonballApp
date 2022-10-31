package com.example.dragonballapp.data.repository

import com.example.dragonballapp.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero

}