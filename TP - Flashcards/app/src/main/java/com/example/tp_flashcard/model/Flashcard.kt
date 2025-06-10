package com.example.tp_flashcard.model

import java.util.UUID

// Represents a flashcard with a question and its answer
data class FlashCard(
    val id: UUID = UUID.randomUUID(),     // Unique identifier for the category
    val categoryId: UUID,                 // Identifier for the category this card belongs to
    val questionResId: Int,               // Text of the question shown to the user
    val answerResId: Int                  // Text of the answer revealed after flipping the card
)

// Represents a category grouping multiple flashcards
data class FlashCardCategory (
    val id: UUID = UUID.randomUUID(),    // Unique identifier for the category
    val nameResId: Int                   // Display name of the category
)