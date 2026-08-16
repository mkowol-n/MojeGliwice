package pl.nepapp.rasoth.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun RasothThemeProvider(isDarkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = remember(isDarkTheme) {
        if(isDarkTheme) {
            darkColors
        } else {
            lightColors
        }
    }

    MaterialTheme(
        colorScheme = materialColors(colors)
    ) {
        CompositionLocalProvider(
            LocalRasothColors provides colors
        ) {
            Surface(
                color = colors.background,
                content = content
            )
        }
    }
}

private val LocalRasothColors = staticCompositionLocalOf<RasothColors> {
    error("No RasothColors provided")
}

object RasothTheme {
    val colors: RasothColors
        @Composable
        @ReadOnlyComposable
        get() = LocalRasothColors.current

    val typography: RasothTypography
        get() = RasothTypography
}