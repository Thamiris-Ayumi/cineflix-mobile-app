package com.example.cineflix.screens.catalog

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MovieDetailScreen(movieName: String, navController: NavController) {
    val encodedMovieName = Uri.encode(movieName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://placehold.co/600x900?text=$encodedMovieName&size=50")
                .crossfade(true)
                .build(),
            contentDescription = movieName,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = movieName,
            fontSize = 28.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
