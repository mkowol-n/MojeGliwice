package pl.nepapp.rasoth.features.login

import org.koin.core.annotation.KoinViewModel
import pl.nepapp.rasoth.core.feature.Async
import pl.nepapp.rasoth.core.feature.BaseViewModel
import pl.nepapp.rasoth.core.feature.Uninitialized
import pl.nepapp.rasoth.core.feature.async
import pl.nepapp.rasoth.core.ui.UiText
import pl.nepapp.rasoth.core.ui.input.InputFieldState
import pl.nepapp.rasoth.core.ui.input.validators.EmailValidator
import pl.nepapp.rasoth.core.ui.input.validators.PasswordValidator


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

        async {
            loginUseCase(
                LoginRequest.Password(
                    email = state.emailField.text.trim(),
                    password = state.passwordField.text,
                )
            )
        }.execute {
            state.copy(
                loginRequestState = it
            )
        }
    }

    fun loginWithGoogle(firebaseIdToken: String, firebaseAccessToken: String? = null) {
        loginWithSocial(
            provider = SocialProvider.GOOGLE,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    fun loginWithFacebook(firebaseIdToken: String, firebaseAccessToken: String? = null) {
        loginWithSocial(
            provider = SocialProvider.FACEBOOK,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    fun loginWithAppleId(firebaseIdToken: String, firebaseAccessToken: String? = null) {
        loginWithSocial(
            provider = SocialProvider.APPLE_ID,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    private fun loginWithSocial(
        provider: SocialProvider,
        firebaseIdToken: String,
        firebaseAccessToken: String?,
    ) {
        intent {
            async {
                loginUseCase(
                    LoginRequest.Social(
                        provider = provider,
                        firebaseIdToken = firebaseIdToken,
                        firebaseAccessToken = firebaseAccessToken,
                    )
                )
            }.execute {
                state.copy(
                    loginRequestState = it
                )
            }
        }
    }
}
