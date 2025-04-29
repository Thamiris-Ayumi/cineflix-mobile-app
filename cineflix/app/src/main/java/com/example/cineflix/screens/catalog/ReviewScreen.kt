package com.example.cineflix.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cineflix.components.navigation.BottomNavigationBar

val reviews = mutableStateListOf<Pair<String, Int>>()

@Composable
fun ReviewScreen(navController: NavController) {
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E313A))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Write a Review",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (i <= rating) Color.Yellow else Color.Gray,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { rating = i }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.DarkGray)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (reviewText.isNotBlank() && rating > 0) {
                        reviews.add(reviewText to rating)
                        reviewText = ""
                        rating = 0
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A5ACD)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit Review", color = Color.White)
            }
        }

        BottomNavigationBar(navController = navController)
    }
}
