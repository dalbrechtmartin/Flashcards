package com.example.tp_flashcard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.R

/**
 * Displays a screen with a list of flashcard categories.
 *
 * @param categories The list of flashcard categories to display
 * @param onCategoryClick Callback function that is invoked when a category is clicked
 */
@Composable
fun HomeScreen(
    categories: List<FlashCardCategory>,
    onCategoryClick: (FlashCardCategory) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AppLogo(
            modifier = Modifier
            .height(100.dp)
            .padding(bottom = 24.dp)
        )

        Text(
            text = stringResource(R.string.home_welcome),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.home_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Text(
            text = stringResource(R.string.home_instruction),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
        )

        CategoryList(categories, onCategoryClick)
    }
}

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    val tintColor = MaterialTheme.colorScheme.onBackground

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = modifier,
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(tintColor)
    )
}

@Composable
fun CategoryList(categories: List<FlashCardCategory>, onCategoryClick: (FlashCardCategory) -> Unit) {
    categories.forEach { category ->
        CategoryItem(category = category, onClick = { onCategoryClick(category) })
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
