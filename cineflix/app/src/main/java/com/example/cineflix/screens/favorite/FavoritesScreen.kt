package com.example.cineflix.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cineflix.components.navigation.BottomNavigationBar

@Composable
fun FavoritesScreen(navController: NavController, favoriteMovies: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
        ) {
            Text(
                text = "My Favorites",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                items(favoriteMovies) { movie ->
                    Text(
                        text = movie,
                        fontSize = 16.sp,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }

        BottomNavigationBar(navController = navController)
    }
}
