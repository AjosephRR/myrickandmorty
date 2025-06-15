package com.ajrr.myrickmorty.data.repository

import android.content.Context
import android.util.Log
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
            Log.d("EPISODIOS", "Cargado desde la API (page: $page, name: $name)")

            // 2. Guardar en Room (solo si no hay búsqueda)
            if (name.isNullOrEmpty()) {
                val entities = remoteEpisodes.map {
                    EpisodeEntity(it.id, it.name, it.episode, it.air_date)
                }
                dao.clearEpisodes()
                dao.insertAll(entities)
                Log.d("EPISODIOS", "Guardados en Room (${entities.size} episodios)")
            }

            // 3. Retornar lo obtenido
            remoteEpisodes
        } catch (e: Exception) {
            Log.e("EPISODIOS", "Error al cargar desde la API: ${e.message}")
            Log.d("EPISODIOS", "Cargando episodios desde Room...")

            val local = dao.getAllEpisodes()
            Log.d("EPISODIOS", "Leídos ${local.size} episodios desde Room")

            local.map {
                Episode(
                    id = it.id,
                    name = it.name,
                    episode = it.episode,
                    air_date = it.air_date,
                    characters = emptyList()
                )
            }
        }
    }

}