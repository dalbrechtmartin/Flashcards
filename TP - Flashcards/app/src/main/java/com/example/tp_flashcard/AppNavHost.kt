package com.example.tp_flashcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.viewmodel.HomeViewModel

@Composable
fun FlashcardNavHost(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
           HomeScreen(homeViewModel = homeViewModel)
        }
    }
}