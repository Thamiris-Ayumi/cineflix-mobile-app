package com.example.cineflix

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cineflix.screens.home.SignIn
import com.example.cineflix.screens.home.Home
import com.example.cineflix.screens.home.Register
import com.example.cineflix.screens.catalog.MoviesScreen
import com.example.cineflix.screens.catalog.ReviewScreen
import com.example.cineflix.screens.catalog.ProfileScreen
import com.example.cineflix.screens.catalog.MovieDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { Home(navController) }
        composable("register") { Register(navController) }
        composable("signIn") { SignIn(navController) }
        composable("movies") { MoviesScreen(navController) }
        composable("review") { ReviewScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("details/{movieName}") { backStackEntry ->
            val movieName = backStackEntry.arguments?.getString("movieName") ?: ""
            MovieDetailScreen(movieName, navController)
        }
    }
}
