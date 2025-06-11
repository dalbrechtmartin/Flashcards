package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.repository.FlashcardRepository
import com.example.tp_flashcard.ui.flashcard.FlashcardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

/**
 * ViewModel responsible for managing flashcard-related operations and state.
 * 
 * This ViewModel:
 * - Maintains the UI state for flashcard sessions
 * - Loads flashcards filtered by category
 * - Tracks progress through a study session including correct/wrong answers
 * - Controls navigation between flashcards
 * - Determines when a study session is complete
 */
class FlashcardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    /**
     * Loads all flashcards associated with the specified category.
     * Updates the UI state with the filtered flashcards and resets the current index to 0.
     *
     * @param categoryId The UUID of the category whose flashcards should be loaded
     */
    fun loadFlashcardsForCategory(categoryId: UUID) {
        val cards = FlashcardRepository.flashcards.filter {
            it.categoryId == categoryId
        }
        _uiState.value = FlashcardUiState(
            currentIndex = 0,
            flashcards = cards
        )
    }

    /**
     * Advances to the next flashcard in the current session.
     * 
     * If there are no more flashcards to display (the current index would exceed the total number of cards),
     * the session is marked as finished. Otherwise, the current index is incremented to point to the next flashcard.
     */
    fun nextFlashcard() {
        val nextIndex = uiState.value.currentIndex + 1
        val totalCards = uiState.value.flashcards.size

        if (nextIndex >= totalCards) {
            _uiState.update { it.copy(isSessionFinished = true) }
        } else {
            _uiState.update { it.copy(currentIndex = nextIndex) }
        }
    }

    /**
     * Marks the current flashcard as correctly answered, increments the correct answer count,
     * advances to the next flashcard, and checks if the session is finished.
     */
    fun markCorrectAndNext() {
        _uiState.update { current ->
            val nextIndex = current.currentIndex + 1
            val sessionFinished = nextIndex >= current.flashcards.size
            current.copy(
                currentIndex = nextIndex,
                correctAnswers = current.correctAnswers + 1,
                isSessionFinished = sessionFinished
            )
        }
    }

    /**
     * Marks the current flashcard as answered incorrectly and advances to the next one.
     */
    fun markWrongAndNext() {
        _uiState.update { current ->
            val nextIndex = current.currentIndex + 1
            val isFinished = nextIndex >= current.flashcards.size
            current.copy(
                wrongAnswers = current.wrongAnswers + 1,
                currentIndex = nextIndex,
                isSessionFinished = isFinished
            )
        }
    }
}