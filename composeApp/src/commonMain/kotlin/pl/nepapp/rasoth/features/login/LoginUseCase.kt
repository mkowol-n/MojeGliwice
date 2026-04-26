package pl.nepapp.rasoth.features.login

import org.koin.core.annotation.Single
import pl.nepapp.rasoth.core.auth.FirebaseAuthClient
import pl.nepapp.rasoth.data.repositories.AccountRepository

@Single
class LoginUseCase(
    private val authClient: FirebaseAuthClient,
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(request: LoginRequest) {
        when (request) {
            is LoginRequest.Password -> authClient.signInWithEmail(
                email = request.email,
                password = request.password,
            )

            is LoginRequest.Social -> {
                accountRepository.login(request)
            }
        }
    }
}
