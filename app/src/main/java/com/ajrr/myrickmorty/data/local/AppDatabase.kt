package com.ajrr.myrickmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EpisodeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "episode_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}