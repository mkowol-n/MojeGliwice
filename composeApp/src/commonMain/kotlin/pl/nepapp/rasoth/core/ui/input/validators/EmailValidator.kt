package pl.nepapp.rasoth.core.ui.input.validators

import pl.nepapp.rasoth.core.ui.UiText
import pl.nepapp.rasoth.core.ui.input.InputValidator
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.field_required
import rasoth.composeapp.generated.resources.invalid_email

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    )

    override fun validate(text: String): UiText? {
        if (text.isBlank()) return UiText.StringRes(Res.string.field_required)
        if (!emailPattern.matches(text)) return UiText.StringRes(Res.string.invalid_email)
        return null
    }
}
