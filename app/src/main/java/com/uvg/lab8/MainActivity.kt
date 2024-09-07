package com.uvg.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.uvg.lab8.screens.LoginScreen
import com.uvg.lab8.screens.CharacterListScreen
import com.uvg.lab8.screens.CharacterDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyApp()
        }
    }
}

@Composable
fun RickAndMortyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("character_list") { CharacterListScreen(navController) }
        composable(
            "character_detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            characterId?.let { CharacterDetailScreen(navController, it) }
        }
    }
}
