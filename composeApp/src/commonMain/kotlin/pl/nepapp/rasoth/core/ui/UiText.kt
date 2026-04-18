package pl.nepapp.rasoth.core.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class Raw(val text: String) : UiText
    data class StringRes(
        val res: StringResource,
        val args: List<Any> = emptyList(),
    ) : UiText

    @Composable
    fun asString(): String = when (this) {
        is Raw -> text
        is StringRes -> stringResource(res, *args.toTypedArray())
    }
}
