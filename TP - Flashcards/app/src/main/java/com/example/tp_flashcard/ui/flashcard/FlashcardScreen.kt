package com.example.tp_flashcard.ui.flashcard

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.R
import com.example.tp_flashcard.viewmodel.FlashcardViewModel
import kotlinx.coroutines.launch
import java.util.*

/**
 * A composable that displays a flashcard study session for a specific category.
 *
 * @param categoryId The UUID of the category containing the flashcards to display
 * @param viewModel The ViewModel that manages the flashcard session state
 * @param onSessionFinished Callback invoked when the user has gone through all flashcards
 * @param modifier Optional modifier for customizing the layout
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlashcardScreen(
    categoryId: UUID,
    viewModel: FlashcardViewModel,
    onSessionFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAnswer by remember { mutableStateOf(false) }

    LaunchedEffect(categoryId) {
        viewModel.loadFlashcardsForCategory(categoryId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!uiState.isSessionFinished) {
            ProgressBar(
                currentIndex = uiState.currentIndex,
                total = uiState.flashcards.size
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedContent(
                    targetState = uiState.flashcards.getOrNull(uiState.currentIndex),
                    transitionSpec = {
                        (slideInHorizontally { width -> width } + fadeIn())
                            .togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
                    },
                    label = "CardSlide"
                ) { targetFlashcard ->
                    targetFlashcard?.let {
                        Flashcard(
                            questionResId = it.questionResId,
                            answerResId = it.answerResId,
                            showAnswer = showAnswer,
                            onCardClick = {
                                showAnswer = !showAnswer
                            }
                        )
                    }
                }
            }

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

    if (uiState.isSessionFinished) {
        ScoreDisplayDialog(
            correctAnswers = uiState.correctAnswers,
            total = uiState.flashcards.size,
            onDismiss = {
                onSessionFinished()
            }
        )
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
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current.density

    val isBack = rotation.value >= 90f
    if (isBack) answerResId else questionResId

    LaunchedEffect(showAnswer) {
        val target = if (showAnswer) 180f else 0f
        scope.launch {
            rotation.animateTo(target, animationSpec = tween(durationMillis = 400))
        }
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onCardClick()
            }
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 8 * density
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (!isBack) {
                Text(
                    text = stringResource(id = questionResId),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
            } else {
                Text(
                    text = stringResource(id = answerResId),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.graphicsLayer {
                        rotationY = 180f // <- Reverse Text
                    }
                )
            }
        }

    }
}

@Composable
fun ScoreDisplayDialog(
    correctAnswers: Int,
    total: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.flashcard_endsession)) },
        text = {
            Text(
                stringResource(
                    R.string.flashcard_score,
                    correctAnswers,
                    total
                )
            )
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.flashcard_return_home))
            }
        }
    )
}

@Composable
fun NextButton(modifier: Modifier = Modifier, onClick: () -> Unit, enabled: Boolean = true) {
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


