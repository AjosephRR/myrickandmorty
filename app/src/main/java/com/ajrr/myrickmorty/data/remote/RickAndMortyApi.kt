package com.ajrr.myrickmorty.data.remote

import com.ajrr.myrickmorty.data.model.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int = 1
    ): EpisodeResponse
}