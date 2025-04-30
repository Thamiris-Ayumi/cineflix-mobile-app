package com.example.cineflix.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineflix.components.navigation.BottomNavigationBar
import com.example.cineflix.data.remote.fetchReviewsByMovie
import com.example.cineflix.data.remote.postReview
import com.example.cineflix.data.remote.model.Review
import kotlinx.coroutines.launch

@Composable
fun MovieDetailScreen(name: String, posterUrl: String, navController: NavController) {
    val reviews = remember { mutableStateListOf<Review>() }
    val scope = rememberCoroutineScope()

    var comment by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(3.0f) }
    var isSent by remember { mutableStateOf(false) }

    LaunchedEffect(name, isSent) {
        val response = fetchReviewsByMovie(name)
        reviews.clear()
        reviews.addAll(response)
        isSent = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = name,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "4.5", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Reviews",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 8.dp)
            ) {
                items(reviews) { review ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "⭐ ${review.rating}",
                            color = Color.Yellow,
                            fontSize = 16.sp
                        )
                        Text(
                            text = review.comment,
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Leave your review:", color = Color.White, fontSize = 16.sp)

            Slider(
                value = rating,
                onValueChange = { rating = it },
                valueRange = 0f..5f,
                steps = 4,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            TextField(
                value = comment,
                onValueChange = { comment = it },
                placeholder = { Text("Comment") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Button(
                onClick = {
                    scope.launch {
                        val result = postReview(
                            Review(
                                movieName = name,
                                rating = rating.toDouble(),
                                comment = comment.trim()
                            )
                        )
                        if (result) {
                            comment = ""
                            rating = 3.0f
                            isSent = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Review")
            }

            if (isSent) {
                Text("✅ Review sent!", color = Color.Green, modifier = Modifier.padding(top = 8.dp))
            }
        }

        BottomNavigationBar(navController)
    }
}
