package com.example.dragonballapp.data.repository

import com.example.dragonballapp.data.local.DragonballDatabase
import com.example.dragonballapp.domain.model.Hero

class LocalDataSourceImpl(
    dragonballDatabase: DragonballDatabase
) : LocalDataSource {

    private val heroDao = dragonballDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}