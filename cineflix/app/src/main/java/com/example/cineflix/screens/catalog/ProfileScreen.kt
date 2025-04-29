package com.example.cineflix.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cineflix.R
import com.example.cineflix.components.navigation.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavController) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_cineflix),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Username",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("signIn") {
                        popUpTo(0)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A5ACD)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Log Out", color = Color.White)
            }
        }

        BottomNavigationBar(navController = navController)
    }
}
