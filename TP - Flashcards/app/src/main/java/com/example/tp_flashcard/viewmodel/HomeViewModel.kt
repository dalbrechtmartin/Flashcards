package com.example.tp_flashcard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.repository.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Home screen that manages FlashCard categories.
 * 
 * This class provides access to the list of FlashCard categories stored in the repository
 * and exposes them through a StateFlow for reactive UI updates.
 * 
 * @property categories A read-only StateFlow containing the list of FlashCard categories.
 */
class HomeViewModel : ViewModel() {
    private val _categories = MutableStateFlow(FlashcardRepository.categories)
    val categories: StateFlow<List<FlashCardCategory>> = _categories.asStateFlow()
}