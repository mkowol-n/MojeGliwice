package pl.nepapp.rasoth.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val blue = Color(0xFF0441B8)
private val white = Color(0xffffffff)
private val whiteVariant1 = Color(0xfff4f4f4)
private val darkGrey = Color(0xff0c161f)
private val blackVariant1 = Color(0xff01010a)
private val black = Color(0xff000000)

data class RasothColors(
    val primary: Color,
    val textColor: Color,
    val background: Color,
    val darkTheme: Boolean
)


val lightColors = RasothColors(
    primary = blue,
    textColor = blackVariant1,
    background = whiteVariant1,
    darkTheme = false,
)

val darkColors = RasothColors(
    primary = blue,
    textColor = whiteVariant1,
    background = blackVariant1,
    darkTheme = true,
)

fun materialColors(colors: RasothColors): ColorScheme {
    if(colors.darkTheme) {
        return darkColorScheme(
            primary = colors.primary,
            background = colors.background,
            surface = colors.background,
        )
    }
    return lightColorScheme(
        primary = colors.primary,
        background = colors.background,
        surface = colors.background
    )

}