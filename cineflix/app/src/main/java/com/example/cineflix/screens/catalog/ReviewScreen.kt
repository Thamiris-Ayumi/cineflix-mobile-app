package com.example.cineflix.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cineflix.components.navigation.BottomNavigationBar
import com.example.cineflix.data.remote.fetchReviews
import com.example.cineflix.data.remote.model.Review
import kotlinx.coroutines.launch

@Composable
fun ReviewScreen(navController: NavController) {
    val reviews = remember { mutableStateListOf<Review>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            val response = fetchReviews()
            reviews.clear()
            reviews.addAll(response)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(reviews) { review ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "🎬 ${review.movieName}",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "⭐ ${review.rating} - ${review.comment}",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        BottomNavigationBar(navController)
    }
}
