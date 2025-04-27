package com.example.cineflix.screens.catalog

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineflix.components.navigation.BottomNavigationBar

@Composable
fun MoviesScreen(navController: NavController) {
    val movies = remember {
        listOf(
            "Interstellar",
            "Inception",
            "The Dark Knight",
            "The Matrix",
            "The Lord of the Rings",
            "Black Panther",
            "Fight Club",
            "Forrest Gump",
            "The Lion King",
            "Gladiator",
            "Titanic",
            "Jurassic Park",
            "The Avengers",
            "Iron Man",
            "Spider-Man No Way Home",
            "Deadpool",
            "Shrek",
            "Frozen",
            "Guardians of the Galaxy",
            "Coco"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(movies) { movieName ->
            val encodedMovieName = Uri.encode(movieName)

            Box(
                modifier = Modifier
                    .aspectRatio(2f / 3f)
                    .background(Color.DarkGray)
                    .clickable {
                        navController.navigate("details/$encodedMovieName")
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://placehold.co/600x900?text=$encodedMovieName&size=40")
                        .crossfade(true)
                        .build(),
                    contentDescription = movieName,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = com.example.cineflix.R.drawable.logo_cineflix),
                    error = painterResource(id = com.example.cineflix.R.drawable.logo_cineflix)
                )
            }
        }

        }

        BottomNavigationBar(navController = navController)
    }
}
