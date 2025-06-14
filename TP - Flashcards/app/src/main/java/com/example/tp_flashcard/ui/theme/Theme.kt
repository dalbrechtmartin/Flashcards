package com.example.tp_flashcard.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit

private val DarkColorScheme = darkColorScheme(
    primary = White,
    secondary = Black,
    tertiary = Black
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    secondary = White,
    tertiary = White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TP_FlashcardTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

fun saveThemePref(context: Context, darkTheme: Boolean) {
    context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        .edit {
            putBoolean("dark_theme", darkTheme)
        }
}

fun loadThemePref(context: Context): Boolean {
    return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        .getBoolean("dark_theme", true)
}