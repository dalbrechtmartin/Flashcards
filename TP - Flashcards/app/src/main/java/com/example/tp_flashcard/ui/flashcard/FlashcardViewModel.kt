package com.example.tp_flashcard.ui.flashcard

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.model.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class FlashcardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    fun loadFlashcardsForCategory(categoryId: UUID) {
        val cards = FlashcardRepository.flashcards.filter {
            it.categoryId == categoryId
        }
        _uiState.value = FlashcardUiState(
            currentIndex = 0,
            flashcards = cards
        )
    }

    fun nextFlashcard() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentIndex + 1
        _uiState.value = currentState.copy(currentIndex = nextIndex)
    }
}
