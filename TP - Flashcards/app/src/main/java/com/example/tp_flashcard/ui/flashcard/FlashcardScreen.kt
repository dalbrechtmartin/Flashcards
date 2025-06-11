package com.example.tp_flashcard.ui.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.R
import com.example.tp_flashcard.viewmodel.FlashcardViewModel
import java.util.UUID

@Composable
fun FlashcardScreen(
    categoryId: UUID,
    viewModel: FlashcardViewModel,
    onSessionFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState().value
    var showAnswer by remember { mutableStateOf(false) }

    LaunchedEffect(categoryId) {
        viewModel.loadFlashcardsForCategory(categoryId)
    }

    if (uiState.isSessionFinished) {
        LaunchedEffect(Unit) {
            onSessionFinished()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressBar(
            currentIndex = uiState.currentIndex,
            total = uiState.flashcards.size
        )

        if (uiState.flashcards.isNotEmpty() && uiState.currentIndex < uiState.flashcards.size) {
            Flashcard(
                questionResId = uiState.flashcards[uiState.currentIndex].questionResId,
                answerResId = uiState.flashcards[uiState.currentIndex].answerResId,
                showAnswer = showAnswer,
                onCardClick = { showAnswer = !showAnswer }
            )
        }

        NextButton(
            enabled = true,
            onNextClick = {
                showAnswer = false
                viewModel.nextFlashcard()
            }
        )
    }
}

@Composable
fun ProgressBar(currentIndex: Int, total: Int) {
    val progress = if (total > 0) currentIndex.toFloat() / total else 0f
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Carte ${currentIndex + 1} / $total")
}

@Composable
fun Flashcard(
    questionResId: Int,
    answerResId: Int,
    showAnswer: Boolean,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = if (showAnswer) answerResId else questionResId),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun NextButton(enabled: Boolean, onNextClick: () -> Unit) {
    Button(
        onClick = onNextClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.flashcard_next))
    }
}
