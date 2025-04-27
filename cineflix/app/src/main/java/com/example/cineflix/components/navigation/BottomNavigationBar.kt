package com.example.cineflix.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF2E313A),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = currentDestination == "movies",
            onClick = {
                if (currentDestination != "movies") {
                    navController.navigate("movies") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Movies") },
            label = { Text("Movies") }
        )
        NavigationBarItem(
            selected = currentDestination == "review",
            onClick = {
                if (currentDestination != "review") {
                    navController.navigate("review") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Filled.Star, contentDescription = "Rate") },
            label = { Text("Rate") }
        )
        NavigationBarItem(
            selected = currentDestination == "profile",
            onClick = {
                if (currentDestination != "profile") {
                    navController.navigate("profile") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
