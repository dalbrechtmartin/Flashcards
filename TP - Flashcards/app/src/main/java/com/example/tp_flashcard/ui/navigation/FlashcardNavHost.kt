package com.example.tp_flashcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.ui.home.HomeViewModel

@Composable
fun FlashcardNavHost(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    val categories by homeViewModel.categories.collectAsState()

    NavHost(navController = navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                categories = categories,
                onCategoryClick = { category ->
                    navController.navigate("${NavRoutes.FLASHCARD}/${category.id}")
                }
            )
        }
        composable("${NavRoutes.FLASHCARD}/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            // Afficher FlashcardScreen avec le categoryId
        }
    }
}