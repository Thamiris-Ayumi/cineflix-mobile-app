package com.example.cineflix.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val name: String,
    val posterUrl: String
)
