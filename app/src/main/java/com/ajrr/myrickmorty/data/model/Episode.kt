package com.ajrr.myrickmorty.data.model

data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>
)

data class EpisodeResponse(
    val results: List<Episode>
)