package com.example.cineflix.screens.catalog

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.example.cineflix.components.navigation.BottomNavigationBar

@Composable
fun MovieDetailScreen(movieName: String, navController: NavController) {
    val encodedMovieName = Uri.encode(movieName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
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
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movieName,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "4.5",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "An epic story of adventure and survival. Watch as the heroes embark on an unforgettable journey.",
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    lineHeight = 22.sp
                )
            }
        }

        BottomNavigationBar(navController = navController)
    }
}
