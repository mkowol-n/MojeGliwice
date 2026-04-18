package pl.nepapp.rasoth.core.ui.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pl.nepapp.rasoth.core.ui.UiText

/**
 * Wraps [TextFieldState] with validation and error visibility logic.
 *
 * Error visibility rules:
 * - Validation errors are shown only after focus is lost.
 * - External errors (set via [setError]) are shown immediately.
 * - Any text change hides the visible error and clears external errors.
 * - [showErrors] forces error visibility (useful for form submission).
 */
@Stable
class InputFieldState(
    initialText: String = "",
    private val validator: InputValidator? = null,
) {
    val textFieldState = TextFieldState(initialText)

    private var validationError: UiText? by mutableStateOf(null)
    private var externalError: UiText? by mutableStateOf(null)
    private var isErrorVisible: Boolean by mutableStateOf(false)

    val error: UiText?
        get() {
            if (!isErrorVisible) return null
            return externalError ?: validationError
        }

    val isError: Boolean get() = error != null

    val text: String get() = textFieldState.text.toString()

    /**
     * Runs the validator against the current text.
     * @return true if valid (no error), false otherwise.
     */
    fun validate(): Boolean {
        validationError = validator?.validate(text)
        return validationError == null
    }

    /**
     * Sets an external error (e.g. from a server response like "Email already exists").
     * The error is shown immediately regardless of focus state.
     * It will be cleared on the next text change.
     */
    fun setError(error: UiText?) {
        externalError = error
        if (error != null) {
            isErrorVisible = true
        }
    }

    /**
     * Forces validation and shows the error if invalid.
     * Useful when the user taps a submit button without interacting with the field.
     */
    fun showErrors() {
        validate()
        isErrorVisible = true
    }

    /**
     * Called internally by [BaseInputField] when the field loses focus.
     */
    internal fun onFocusLost() {
        validate()
        isErrorVisible = true
    }

    /**
     * Called internally by [BaseInputField] when the text changes.
     */
    internal fun onTextChanged() {
        validate()
        externalError = null
        isErrorVisible = false
    }
}
