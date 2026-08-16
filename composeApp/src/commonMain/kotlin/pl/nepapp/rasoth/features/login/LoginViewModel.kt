package pl.nepapp.rasoth.features.login

import org.koin.core.annotation.KoinViewModel
import pl.nepapp.rasoth.core.auth.SocialAuthClient
import pl.nepapp.rasoth.core.feature.Async
import pl.nepapp.rasoth.core.feature.Fail
import pl.nepapp.rasoth.core.feature.Loading
import pl.nepapp.rasoth.core.feature.BaseViewModel
import pl.nepapp.rasoth.core.feature.Success
import pl.nepapp.rasoth.core.feature.Uninitialized
import pl.nepapp.rasoth.core.feature.async
import pl.nepapp.rasoth.core.ui.UiText
import pl.nepapp.rasoth.core.ui.input.InputFieldState
import pl.nepapp.rasoth.core.ui.input.validators.EmailValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordValidator
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.login_error_unknown


data class LoginState(
    val loginRequestState: Async<Unit> = Uninitialized,
    val isLoading: Boolean = false,
    val emailField: InputFieldState = InputFieldState(validator = EmailValidator()),
    val passwordField: InputFieldState = InputFieldState(validator = PasswordValidator()),
    val authError: UiText? = null,
)

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, Nothing>(LoginState()) {

    fun login() = intent {
        state.emailField.showErrors()
        state.passwordField.showErrors()

        val isEmailValid = state.emailField.validate()
        val isPasswordValid = state.passwordField.validate()
        if (!isEmailValid || !isPasswordValid) return@intent

        performLogin(
            LoginRequest.Password(
                email = state.emailField.text.trim(),
                password = state.passwordField.text,
            )
        )
    }

    fun loginWithSocial(
        provider: SocialProvider,
        firebaseIdToken: String,
        firebaseAccessToken: String?,
    ) {
        intent {
            performLogin(
                LoginRequest.Social(
                    provider = provider,
                    firebaseIdToken = firebaseIdToken,
                    firebaseAccessToken = firebaseAccessToken,
                )
            )
        }
    }

    private suspend fun org.orbitmvi.orbit.syntax.Syntax<LoginState, Nothing>.performLogin(request: LoginRequest) {
        async {
            loginUseCase(request)
        }.handleError {
            reduce {
                state.copy(authError = UiText.StringRes(Res.string.login_error_unknown))
            }
            true
        }.execute { asyncState ->
            state.copy(
                loginRequestState = asyncState,
            )
        }
    }
}
