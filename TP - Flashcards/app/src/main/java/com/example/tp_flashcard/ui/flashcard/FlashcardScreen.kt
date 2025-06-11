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

/**
 * A composable that displays a flashcard study session for a specific category.
 *
 * @param categoryId The UUID of the category containing the flashcards to display
 * @param viewModel The ViewModel that manages the flashcard session state
 * @param onSessionFinished Callback invoked when the user has gone through all flashcards
 * @param modifier Optional modifier for customizing the layout
 */
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (uiState.isSessionFinished) {
            ScoreDisplay(
                correctAnswers = uiState.correctAnswers,
                total = uiState.flashcards.size
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSessionFinished,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.flashcard_return_home))
            }

        } else if (uiState.flashcards.isNotEmpty() && uiState.currentIndex < uiState.flashcards.size) {
            ProgressBar(
                currentIndex = uiState.currentIndex,
                total = uiState.flashcards.size
            )

            Flashcard(
                questionResId = uiState.flashcards[uiState.currentIndex].questionResId,
                answerResId = uiState.flashcards[uiState.currentIndex].answerResId,
                showAnswer = showAnswer,
                onCardClick = { showAnswer = !showAnswer }
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DontKnowButton(
                    onClick = {
                        showAnswer = false
                        viewModel.markWrongAndNext()
                    },
                    modifier = Modifier.weight(1f)
                )
                NextButton(
                    onClick = {
                        showAnswer = false
                        viewModel.markCorrectAndNext()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ProgressBar(currentIndex: Int, total: Int) {
    val displayIndex = (currentIndex + 1).coerceAtMost(total)
    val progress = if (total > 0) currentIndex.toFloat() / total else 0f

    Column {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.flashcard_progress, displayIndex, total),
            style = MaterialTheme.typography.bodyMedium
        )
    }
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
fun ScoreDisplay(correctAnswers: Int, total: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.flashcard_score, correctAnswers, total),
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun NextButton(onClick: () -> Unit, enabled: Boolean = true, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.flashcard_next))
    }
}

@Composable
fun DontKnowButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.flashcard_dont_know))
    }
}

