package com.example.tp_flashcard.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.model.FlashCardCategory

@Composable
fun HomeScreen(
    categories: List<FlashCardCategory>,
    onCategoryClick: (FlashCardCategory) -> Unit
) {
    Column {
        categories.forEach { category ->
            CategoryItem(category = category, onClick = { onCategoryClick(category) })
        }
    }
}

@Composable
fun CategoryItem(category: FlashCardCategory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = stringResource(category.nameResId),
            modifier = Modifier.padding(16.dp)
        )
    }
}
