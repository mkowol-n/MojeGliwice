package pl.nepapp.rasoth.core.ui.input

import pl.nepapp.rasoth.core.ui.UiText

fun interface InputValidator {
    /**
     * Validates the given text.
     * @return [UiText] describing the error, or null if the text is valid.
     */
    fun validate(text: String): UiText?
}
