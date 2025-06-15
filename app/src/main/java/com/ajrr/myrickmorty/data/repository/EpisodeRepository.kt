package com.ajrr.myrickmorty.data.repository

import android.content.Context
import com.ajrr.myrickmorty.data.local.AppDatabase
import com.ajrr.myrickmorty.data.local.EpisodeEntity
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.data.remote.RetrofitInstance

class EpisodeRepository(context: Context) {

    private val dao = AppDatabase.getDatabase(context).episodeDao()

    suspend fun getEpisodes(page: Int = 1, name: String? = null): List<Episode> {
        return try {
            // 1. Obtener de la API
            val remoteEpisodes = RetrofitInstance.api.getEpisodes(page, name).results

            // 2. Guardar en Room (solo si no hay búsqueda)
            if (name.isNullOrEmpty()) {
                val entities = remoteEpisodes.map {
                    EpisodeEntity(it.id, it.name, it.episode, it.air_date)
                }
                dao.clearEpisodes()
                dao.insertAll(entities)
            }

            // 3. Retornar lo obtenido
            remoteEpisodes
        } catch (e: Exception) {
            // 4. Si falla, obtener desde Room
            val local = dao.getAllEpisodes()
            local.map {
                Episode(
                    id = it.id,
                    name = it.name,
                    episode = it.episode,
                    air_date = it.air_date,
                    characters = emptyList() // Solo si no necesitas personajes locales
                )
            }
        }
    }
}