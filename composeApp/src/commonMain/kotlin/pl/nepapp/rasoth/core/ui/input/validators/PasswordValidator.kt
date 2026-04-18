package pl.nepapp.rasoth.core.ui.input.validators

import pl.nepapp.rasoth.core.ui.UiText
import pl.nepapp.rasoth.core.ui.input.InputValidator
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.field_required
import rasoth.composeapp.generated.resources.password_needs_digit
import rasoth.composeapp.generated.resources.password_needs_lowercase
import rasoth.composeapp.generated.resources.password_needs_special_char
import rasoth.composeapp.generated.resources.password_needs_uppercase
import rasoth.composeapp.generated.resources.password_too_short

class PasswordValidator : InputValidator {

    companion object {
        private const val MIN_LENGTH = 8
        private const val SPECIAL_CHARACTERS = "!@#\$%^&*()_+-=[]{}|;':\",./<>?"
    }

    override fun validate(text: String): UiText? {
        if (text.isBlank()) return UiText.StringRes(Res.string.field_required)
        if (text.length < MIN_LENGTH) return UiText.StringRes(Res.string.password_too_short)
        if (text.none { it.isDigit() }) return UiText.StringRes(Res.string.password_needs_digit)
        if (text.none { it.isLowerCase() }) return UiText.StringRes(Res.string.password_needs_lowercase)
        if (text.none { it.isUpperCase() }) return UiText.StringRes(Res.string.password_needs_uppercase)
        if (text.none { it in SPECIAL_CHARACTERS }) return UiText.StringRes(Res.string.password_needs_special_char)
        return null
    }
}
