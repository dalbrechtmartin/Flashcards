package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.repository.FlashcardRepository
import com.example.tp_flashcard.ui.flashcard.FlashcardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        val nextIndex = uiState.value.currentIndex + 1
        val totalCards = uiState.value.flashcards.size

        if (nextIndex >= totalCards) {
            _uiState.update { it.copy(isSessionFinished = true) }
        } else {
            _uiState.update { it.copy(currentIndex = nextIndex) }
        }
    }

}