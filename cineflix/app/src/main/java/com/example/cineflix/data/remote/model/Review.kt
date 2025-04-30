package com.example.cineflix.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val movieName: String,
    val rating: Double,
    val comment: String
)
