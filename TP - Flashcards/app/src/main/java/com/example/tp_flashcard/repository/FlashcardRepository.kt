package com.example.tp_flashcard.repository

import com.example.tp_flashcard.R
import com.example.tp_flashcard.model.FlashCard
import com.example.tp_flashcard.model.FlashCardCategory

/**
 * Repository for accessing flashcard data in the application.
 * 
 * This singleton object provides access to predefined flashcard categories and cards.
 * It serves as a static data source for flashcard information within the application.
 */
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
        // Plants
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_sauge, answerResId = R.string.a_sauge),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_lavande, answerResId = R.string.a_lavande),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_romarin, answerResId = R.string.a_romarin),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_menthe, answerResId = R.string.a_menthe),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_camomille, answerResId = R.string.a_camomille),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_thym, answerResId = R.string.a_thym),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_basilic, answerResId = R.string.a_basilic),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_ortie, answerResId = R.string.a_ortie),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_achillee, answerResId = R.string.a_achillee),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_aloes, answerResId = R.string.a_aloes),
        FlashCard(categoryId = categories[0].id, questionResId = R.string.q_rose, answerResId = R.string.a_rose),

        // Moon
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune, answerResId = R.string.a_lune),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_nouvelle, answerResId = R.string.a_lune_nouvelle),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_croissante, answerResId = R.string.a_lune_croissante),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_gibbeuse, answerResId = R.string.a_lune_gibbeuse),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_decroissante, answerResId = R.string.a_lune_decroissante),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_noire, answerResId = R.string.a_lune_noire),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_bleue, answerResId = R.string.a_lune_bleue),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_eclipse_lunaire, answerResId = R.string.a_eclipse_lunaire),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_eclipse_solaire, answerResId = R.string.a_eclipse_solaire),
        FlashCard(categoryId = categories[1].id, questionResId = R.string.q_lune_rouge, answerResId = R.string.a_lune_rouge),

        // Sabbats
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_samhain, answerResId = R.string.a_samhain),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_yule, answerResId = R.string.a_yule),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_imbolc, answerResId = R.string.a_imbolc),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_ostara, answerResId = R.string.a_ostara),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_beltane, answerResId = R.string.a_beltane),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_litha, answerResId = R.string.a_litha),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_lammas, answerResId = R.string.a_lammas),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_mabon, answerResId = R.string.a_mabon),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_sabbat_printemps, answerResId = R.string.a_sabbat_printemps),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_sabbat_ete, answerResId = R.string.a_sabbat_ete),
        FlashCard(categoryId = categories[2].id, questionResId = R.string.q_sabbat_automne, answerResId = R.string.a_sabbat_automne),

        // Symboles
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_pentacle, answerResId = R.string.a_pentacle),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_triple_lune, answerResId = R.string.a_triple_lune),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_ankh, answerResId = R.string.a_ankh),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_triskel, answerResId = R.string.a_triskel),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_oeil_horus, answerResId = R.string.a_oeil_horus),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_arbre_vie, answerResId = R.string.a_arbre_vie),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_rune_algiz, answerResId = R.string.a_rune_algiz),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_trefle, answerResId = R.string.a_trefle),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_main_fatima, answerResId = R.string.a_main_fatima),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_croix_ansée, answerResId = R.string.a_croix_ansée),
        FlashCard(categoryId = categories[3].id, questionResId = R.string.q_roue_annee, answerResId = R.string.a_roue_annee)
    )
}