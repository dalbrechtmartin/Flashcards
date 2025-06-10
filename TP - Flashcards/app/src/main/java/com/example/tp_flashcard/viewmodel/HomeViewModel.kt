package com.example.tp_flashcard.viewmodel

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.model.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale.Category

class HomeViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<FlashCardCategory>>(emptyList())

    val categories: StateFlow<List<FlashCardCategory>> = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = FlashcardRepository.categories
    }
}
