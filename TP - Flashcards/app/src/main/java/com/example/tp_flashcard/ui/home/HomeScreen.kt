package com.example.tp_flashcard.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.viewmodel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val categories by homeViewModel.categories.collectAsState()

    CategoryList(categories = categories)
}

@Composable
private fun CategoryList(categories: List<FlashCardCategory>) {
    LazyColumn {
        items(categories) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
private fun CategoryItem(category: FlashCardCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = stringResource(category.nameResId),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
