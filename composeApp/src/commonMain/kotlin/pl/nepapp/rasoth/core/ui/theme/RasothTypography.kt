package pl.nepapp.rasoth.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.manrope_bold
import rasoth.composeapp.generated.resources.manrope_medium
import rasoth.composeapp.generated.resources.manrope_regular
import rasoth.composeapp.generated.resources.manrope_semibold

object RasothTypography {

    @Composable
    private fun regular() =
        TextStyle(
            fontFamily = rasothFontFamily(),
            fontWeight = FontWeight.W400,
            color = RasothTheme.colors.textColor
        )

    @Composable
    private fun medium() =
        TextStyle(
            fontFamily = rasothFontFamily(),
            fontWeight = FontWeight.W500,
            color = RasothTheme.colors.textColor
        )

    @Composable
    private fun semiBold() =
        TextStyle(
            fontFamily = rasothFontFamily(),
            fontWeight = FontWeight.W600,
            color = RasothTheme.colors.textColor
        )

    @Composable
    private fun bold() =
        TextStyle(
            fontFamily = rasothFontFamily(),
            fontWeight = FontWeight.W700,
            color = RasothTheme.colors.textColor
        )

    @Composable
    fun regular16() = regular().copy(
        fontSize = 16.sp
    )
}

@Composable
private fun rasothFontFamily(): FontFamily {
    return FontFamily(
        Font(
            resource = Res.font.manrope_regular,
            style = FontStyle.Normal,
            weight = FontWeight.W400,
        ),
        Font(
            resource = Res.font.manrope_medium,
            style = FontStyle.Normal,
            weight = FontWeight.W500,
        ),
        Font(
            resource = Res.font.manrope_semibold,
            style = FontStyle.Normal,
            weight = FontWeight.W600,
        ),
        Font(
            resource = Res.font.manrope_bold,
            style = FontStyle.Normal,
            weight = FontWeight.W700,
        ),
    )
}