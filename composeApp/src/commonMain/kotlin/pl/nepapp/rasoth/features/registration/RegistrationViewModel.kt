package pl.nepapp.rasoth.features.registration

import kotlinx.coroutines.delay
import org.koin.core.annotation.KoinViewModel
import pl.nepapp.rasoth.core.feature.BaseViewModel
import pl.nepapp.rasoth.core.ui.input.InputFieldState
import pl.nepapp.rasoth.core.ui.input.validators.EmailValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordMatchValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordValidator

class RegistrationState(
    val isLoading: Boolean = false,
    val emailField: InputFieldState,
    val passwordField: InputFieldState,
    val repeatPasswordField: InputFieldState,
)

@KoinViewModel
class RegistrationViewModel : BaseViewModel<RegistrationState, Nothing>(
    run {
        val passwordField = InputFieldState(validator = PasswordValidator())
        RegistrationState(
            emailField = InputFieldState(validator = EmailValidator()),
            passwordField = passwordField,
            repeatPasswordField = InputFieldState(validator = PasswordMatchValidator(passwordField)),
        )
    }
) {

    fun register() = intent {
        state.emailField.showErrors()
        state.passwordField.showErrors()
        state.repeatPasswordField.showErrors()

        val isEmailValid = state.emailField.validate()
        val isPasswordValid = state.passwordField.validate()
        val isRepeatValid = state.repeatPasswordField.validate()
        if (!isEmailValid || !isPasswordValid || !isRepeatValid) return@intent

        reduce { RegistrationState(
            isLoading = true,
            emailField = state.emailField,
            passwordField = state.passwordField,
            repeatPasswordField = state.repeatPasswordField,
        ) }

        delay(5000)
        // Simulate API call

        reduce { RegistrationState(
            isLoading = false,
            emailField = state.emailField,
            passwordField = state.passwordField,
            repeatPasswordField = state.repeatPasswordField,
        ) }
    }
}
