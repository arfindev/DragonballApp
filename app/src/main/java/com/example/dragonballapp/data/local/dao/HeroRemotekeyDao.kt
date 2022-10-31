package com.example.dragonballapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dragonballapp.domain.model.HeroRemoteKeys


@Dao
interface HeroRemotekeyDao {

    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :heroId")
    suspend fun getRemoteKey(heroId: Int): HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}