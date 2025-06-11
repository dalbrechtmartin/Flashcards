package com.example.tp_flashcard.ui.flashcard

import com.example.tp_flashcard.model.FlashCard

data class FlashcardUiState(
    val currentIndex: Int = 0,
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0,
    val flashcards: List<FlashCard> = emptyList(),
    val isSessionFinished: Boolean = false
)
