package pl.nepapp.rasoth.features.login

import kotlinx.coroutines.delay
import org.koin.core.annotation.KoinViewModel
import pl.nepapp.rasoth.core.feature.BaseViewModel
import pl.nepapp.rasoth.core.ui.input.InputFieldState
import pl.nepapp.rasoth.core.ui.input.validators.EmailValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordValidator


data class LoginState(
    val isLoading: Boolean = false,
    val emailField: InputFieldState = InputFieldState(validator = EmailValidator()),
    val passwordField: InputFieldState = InputFieldState(validator = PasswordValidator())
)

@KoinViewModel
class LoginViewModel : BaseViewModel<LoginState, Nothing>(LoginState()) {

    fun login() = intent {
        state.emailField.showErrors()
        state.passwordField.showErrors()

        val isEmailValid = state.emailField.validate()
        val isPasswordValid = state.passwordField.validate()
        if (!isEmailValid || !isPasswordValid) return@intent

        reduce { state.copy(isLoading = true) }

        delay(5000)
        // Simulate API call — in real app this would call a repository
        // Example of setting a server-side error:
        // state.emailField.setError(UiText.Raw("Email already exists"))

        reduce { state.copy(isLoading = false) }
    }
}
