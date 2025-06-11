package com.example.tp_flashcard.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.MainActivity
import com.example.tp_flashcard.model.FlashCardCategory
import com.example.tp_flashcard.R
import com.example.tp_flashcard.viewmodel.setAppLocale

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

        Spacer(modifier = Modifier.height(16.dp))

        LanguageSelector(context = LocalContext.current)
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

@Composable
fun LanguageSelector(context: Context) {
    var showDialog by remember { mutableStateOf(false) }

    Button(onClick = { showDialog = true }) {
        Text(stringResource(id = R.string.change_language))
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.dialog_language_title)) },
            text = { Text(stringResource(R.string.dialog_language_text)) },
            confirmButton = {
                Button(onClick = {
                    setAppLocale(context, "fr")
                    showDialog = false
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    if (context is Activity) {
                        context.finish()
                    }
                }) { Text(stringResource(R.string.dialog_language_french)) }
            },
            dismissButton = {
                Button(onClick = {
                    setAppLocale(context, "en")
                    showDialog = false
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    if (context is Activity) {
                        context.finish()
                    }
                }) { Text(stringResource(R.string.dialog_language_english)) }
            }
        )
    }
}
