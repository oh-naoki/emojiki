package me.ohnaoki.emojiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") { MenuScreen(navController = navController) }
                composable("game") { GameScreen(navController = navController) }
                composable(
                    "result/{gameClearTime}",
                    arguments = listOf(navArgument("gameClearTime") { type = NavType.LongType })
                ) {
                    ResultScreen(
                        gameClearTime = it.arguments?.getLong("gameClearTime", 0L) ?: 0L,
                        navController = navController
                    )
                }
            }
        }
    }
}


