package com.example.tp_flashcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.flashcard.FlashcardViewModel
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.ui.home.HomeViewModel
import com.example.tp_flashcard.ui.flashcard.FlashcardScreen
import java.util.UUID

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
            val categoryIdString = backStackEntry.arguments?.getString("categoryId")
            val categoryId = UUID.fromString(categoryIdString)
            val flashcardViewModel: FlashcardViewModel = viewModel()

            if (categoryId != null) {
                FlashcardScreen(
                    categoryId = categoryId,
                    viewModel = flashcardViewModel,
                    onSessionFinished = {
                        navController.navigate(NavRoutes.HOME)
                    }
                )
            }
        }
    }
}