package pl.nepapp.rasoth.features.login

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
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, Nothing>(LoginState()) {

    fun login() = intent {
        state.emailField.showErrors()
        state.passwordField.showErrors()

        val isEmailValid = state.emailField.validate()
        val isPasswordValid = state.passwordField.validate()
        if (!isEmailValid || !isPasswordValid) return@intent

        reduce { state.copy(isLoading = true) }

        runCatching {
            loginUseCase(
                LoginRequest.Password(
                    email = state.emailField.text.trim(),
                    password = state.passwordField.text,
                )
            )
        }

        reduce { state.copy(isLoading = false) }
    }

    fun loginWithGoogle(firebaseIdToken: String, firebaseAccessToken: String? = null) = intent {
        loginWithSocial(
            provider = SocialProvider.GOOGLE,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    fun loginWithFacebook(firebaseIdToken: String, firebaseAccessToken: String? = null) = intent {
        loginWithSocial(
            provider = SocialProvider.FACEBOOK,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    fun loginWithAppleId(firebaseIdToken: String, firebaseAccessToken: String? = null) = intent {
        loginWithSocial(
            provider = SocialProvider.APPLE_ID,
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }

    private suspend fun loginWithSocial(
        provider: SocialProvider,
        firebaseIdToken: String,
        firebaseAccessToken: String?,
    ) {
        intent {
            reduce { state.copy(isLoading = true) }

            runCatching {
                loginUseCase(
                    LoginRequest.Social(
                        provider = provider,
                        firebaseIdToken = firebaseIdToken,
                        firebaseAccessToken = firebaseAccessToken,
                    )
                )
            }

            reduce { state.copy(isLoading = false) }
        }
    }
}
