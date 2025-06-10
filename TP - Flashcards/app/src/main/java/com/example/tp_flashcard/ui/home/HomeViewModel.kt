package com.example.tp_flashcard.ui.home

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.model.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _categories = MutableStateFlow(FlashcardRepository.categories)
    val categories: StateFlow<List<FlashCardCategory>> = _categories.asStateFlow()
}
