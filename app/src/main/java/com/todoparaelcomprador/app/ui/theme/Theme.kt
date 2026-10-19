package com.todoparaelcomprador.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary          = AzulPrincipal,
    onPrimary        = Color.White,
    primaryContainer = AzulClaro,
    secondary        = RojoAcento,
    onSecondary      = Color.White,
    background       = FondoClaro,
    onBackground     = TextoOscuro,
    surface          = Color.White,
    onSurface        = TextoOscuro,
    surfaceVariant   = GrisClaro
)

private val DarkColors = darkColorScheme(
    primary    = AzulClaro,
    onPrimary  = Color.Black,
    secondary  = RojoAcento,
    background = Color(0xFF121212),
    surface    = Color(0xFF1E1E1E)
)

@Composable
fun TodoParaElCompradorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}