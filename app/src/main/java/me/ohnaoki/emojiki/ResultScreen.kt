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

@Composable
fun ResultScreen(gameClearTime: Long, navController: NavController) {
    ResultScreenContent(
        gameClearTime = gameClearTime,
        onTapTopScreen = { navController.navigate("menu") })
}

@Composable
fun ResultScreenContent(gameClearTime: Long, onTapTopScreen: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Time: $gameClearTime sec")
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                onClick = onTapTopScreen
            ) {
                Text(text = "🎮 Back to Title")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResultScreenContent() {
    ResultScreenContent(gameClearTime = 10L, onTapTopScreen = {})
}