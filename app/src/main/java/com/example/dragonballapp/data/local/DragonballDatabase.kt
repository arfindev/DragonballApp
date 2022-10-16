package com.example.dragonballapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dragonballapp.data.local.dao.DatabaseConverter
import com.example.dragonballapp.data.local.dao.HeroDao
import com.example.dragonballapp.data.local.dao.HeroRemotekeyDao
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)

@TypeConverters(DatabaseConverter::class)
abstract class DragonballDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao

    abstract fun heroRemoteKeydao(): HeroRemotekeyDao
}