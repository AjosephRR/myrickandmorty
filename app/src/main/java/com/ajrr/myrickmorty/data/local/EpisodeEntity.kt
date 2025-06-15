package com.ajrr.myrickmorty.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val episode: String,
    val air_date: String
)