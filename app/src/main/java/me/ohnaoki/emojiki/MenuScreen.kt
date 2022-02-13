package me.ohnaoki.emojiki

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                onClick = { navController.navigate("game") }) {
                Text(text = "🎮 Game Start")
            }
        }

    }
}

@Preview
@Composable
fun PreviewMenuScreen() {
    MenuScreen(rememberNavController())
}