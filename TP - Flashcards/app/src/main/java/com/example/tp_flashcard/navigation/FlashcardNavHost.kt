package com.example.tp_flashcard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.viewmodel.FlashcardViewModel
import com.example.tp_flashcard.ui.home.HomeScreen
import com.example.tp_flashcard.viewmodel.HomeViewModel
import com.example.tp_flashcard.ui.flashcard.FlashcardScreen
import java.util.UUID

/**
 * Main navigation component for the Flashcard application.
 *
 * This composable function sets up the navigation structure using Jetpack Compose Navigation.
 * It defines the navigation routes between the home screen and flashcard screen, and handles
 * the passing of data between destinations.
 *
 * @param homeViewModel The view model that provides categories data to the home screen.
 *                     Categories are collected as state and passed to the HomeScreen.
 */
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