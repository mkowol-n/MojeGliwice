package pl.nepapp.rasoth.features.login

import org.koin.core.annotation.Single
import pl.nepapp.rasoth.data.repositories.AccountRepository

@Single
class LoginUseCase(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(request: LoginRequest) {
        val tokens = accountRepository.login(request)
    }
}
