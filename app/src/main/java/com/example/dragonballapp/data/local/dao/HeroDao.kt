package com.example.dragonballapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dragonballapp.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM dragonball_hero_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>


    @Query("SELECT * FROM dragonball_hero_table WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM dragonball_hero_table")
    suspend fun deleteHeroes()

}