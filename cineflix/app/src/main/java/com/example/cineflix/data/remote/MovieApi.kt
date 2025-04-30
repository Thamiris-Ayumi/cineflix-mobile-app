package com.example.cineflix.data.remote

import com.example.cineflix.data.remote.model.Movie
import com.example.cineflix.data.remote.model.Review
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val client = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

suspend fun fetchReviews(): List<Review> {
    return try {
        client.get("http://10.0.2.2:8080/reviews").body()
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

suspend fun fetchMovies(): List<Movie> {
    return try {
        client.get("http://10.0.2.2:8080/movies").body()
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}


suspend fun postReview(review: Review): Boolean {
    return try {
        client.post("http://10.0.2.2:8080/api/reviews") {
            contentType(ContentType.Application.Json)
            setBody(review)
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

suspend fun fetchReviewsByMovie(movieName: String): List<Review> {
    return try {
        client.get("http://10.0.2.2:8080/api/reviews") {
            url {
                parameters.append("movieName", movieName)
            }
        }.body()
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
