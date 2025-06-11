package com.example.tp_flashcard.repository

import com.example.tp_flashcard.R
import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory

object FlashcardRepository {

    // Available categories
    val categories: List<FlashCardCategory> = listOf(
        FlashCardCategory(nameResId = R.string.category_plantes),
        FlashCardCategory(nameResId = R.string.category_lune),
        FlashCardCategory(nameResId = R.string.category_sabbats),
        FlashCardCategory(nameResId = R.string.category_symboles)
    )

    // Cards associated
    val flashcards: List<FlashCard> = listOf(
        FlashCard(
            categoryId = categories[0].id,
            questionResId = R.string.q_sauge,
            answerResId = R.string.a_sauge
        ),
        FlashCard(
            categoryId = categories[1].id,
            questionResId = R.string.q_lune,
            answerResId = R.string.a_lune
        ),
        FlashCard(
            categoryId = categories[2].id,
            questionResId = R.string.q_samhain,
            answerResId = R.string.a_samhain
        ),
        FlashCard(
            categoryId = categories[3].id,
            questionResId = R.string.q_pentacle,
            answerResId = R.string.a_pentacle
        )
    )
}