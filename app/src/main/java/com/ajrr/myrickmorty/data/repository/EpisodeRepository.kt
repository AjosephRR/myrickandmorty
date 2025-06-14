package com.ajrr.myrickmorty.data.repository

import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.data.remote.RetrofitInstance

class EpisodeRepository {
    suspend fun getEpisodes(page: Int = 1): List<Episode> {
        return RetrofitInstance.api.getEpisodes(page).results
    }
}