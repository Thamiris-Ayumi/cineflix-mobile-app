package com.example.cineflix.screens.catalog

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineflix.R
import com.example.cineflix.components.navigation.BottomNavigationBar
import com.example.cineflix.data.remote.fetchMovies
import com.example.cineflix.data.remote.model.Movie
import kotlinx.coroutines.launch

@Composable
fun MoviesScreen(navController: NavController) {
    val movies = remember { mutableStateListOf<Movie>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            val response = fetchMovies()
            movies.clear()
            movies.addAll(response)
        }
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
            items(movies) { movie ->
                Box(
                    modifier = Modifier
                        .aspectRatio(2f / 3f)
                        .background(Color.DarkGray)
                        .clickable {
                            val encodedName = Uri.encode(movie.name)
                            val encodedPosterUrl = Uri.encode(movie.posterUrl)
                            navController.navigate("details/$encodedName/$encodedPosterUrl")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.posterUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = movie.name,
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(id = R.drawable.logo_cineflix),
                        error = painterResource(id = R.drawable.logo_cineflix)
                    )
                }
            }
        }

        BottomNavigationBar(navController = navController)
    }
}
