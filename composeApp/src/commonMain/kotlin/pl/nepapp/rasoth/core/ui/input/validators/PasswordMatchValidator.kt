package pl.nepapp.rasoth.core.ui.input.validators

import pl.nepapp.rasoth.core.ui.UiText
import pl.nepapp.rasoth.core.ui.input.InputFieldState
import pl.nepapp.rasoth.core.ui.input.InputValidator
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.field_required
import rasoth.composeapp.generated.resources.passwords_do_not_match

class PasswordMatchValidator(
    private val originalPasswordField: InputFieldState,
) : InputValidator {

    override fun validate(text: String): UiText? {
        if (text.isBlank()) return UiText.StringRes(Res.string.field_required)
        if (text != originalPasswordField.text) return UiText.StringRes(Res.string.passwords_do_not_match)
        return null
    }
}
