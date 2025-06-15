package com.ajrr.myrickmorty.data.local

import androidx.room.*

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<EpisodeEntity>)

    @Query("SELECT * FROM episodes")
    suspend fun getAllEpisodes(): List<EpisodeEntity>

    @Query("DELETE FROM episodes")
    suspend fun clearEpisodes()
}