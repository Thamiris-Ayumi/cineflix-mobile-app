package com.example.cineflix.screens.catalog

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineflix.components.navigation.BottomNavigationBar
import com.example.cineflix.R

data class Movie(val name: String, val posterUrl: String)

@Composable
fun MoviesScreen(navController: NavController) {
    val movies = remember {
        listOf(
            Movie("Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg"),
            Movie("Inception", "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg"),
            Movie("The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"),
            Movie("The Matrix", "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg"),
            Movie("The Lord of the Rings", "https://image.tmdb.org/t/p/w500/56zTpe2xvaA4alU51sRWPoKPYZy.jpg"),
            Movie("Black Panther", "https://image.tmdb.org/t/p/w500/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"),
            Movie("Fight Club", "https://image.tmdb.org/t/p/w500/bptfVGEQuv6vDTIMVCHjJ9Dz8PX.jpg"),
            Movie("Forrest Gump", "https://image.tmdb.org/t/p/w500/saHP97rTPS5eLmrLQEcANmKrsFl.jpg"),
            Movie("The Lion King", "https://image.tmdb.org/t/p/w500/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg"),
            Movie("Dune", "https://image.tmdb.org/t/p/w500/d5NXSklXo0qyIYkgV94XAgMIckC.jpg"),
            Movie("Titanic", "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg"),
            Movie("Jurassic Park", "https://image.tmdb.org/t/p/w500/9i3plLl89DHMz7mahksDaAo7HIS.jpg"),
            Movie("The Avengers", "https://image.tmdb.org/t/p/w500/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg"),
            Movie("Iron Man", "https://image.tmdb.org/t/p/w500/78lPtwv72eTNqFW9COBYI0dWDJa.jpg"),
            Movie("Spider-Man No Way Home", "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"),
            Movie("Deadpool", "https://image.tmdb.org/t/p/w500/fSRb7vyIP8rQpL0I47P3qUsEKX3.jpg"),
            Movie("Shrek", "https://image.tmdb.org/t/p/w500/iB64vpL3dIObOtMZgX3RqdVdQDc.jpg"),
            Movie("Frozen", "https://image.tmdb.org/t/p/w500/kgwjIb2JDHRhNk13lmSxiClFjVk.jpg"),
            Movie("Top Gun: Maverick", "https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg"),
            Movie("Coco", "https://image.tmdb.org/t/p/w500/gGEsBPAijhVUFoiNpgZXqRVWJt2.jpg")
        )
    }

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
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies) { movie ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(2f / 3f)
                            .background(Color.DarkGray)
                            .clickable {
                                val encodedName = Uri.encode(movie.name)
                                navController.navigate("details/$encodedName")
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
        }

        BottomNavigationBar(navController = navController)
    }
}
