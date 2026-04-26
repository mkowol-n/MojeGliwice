package pl.nepapp.rasoth.data.repositories

import io.ktor.client.call.body
import org.koin.core.annotation.Single
import pl.nepapp.rasoth.data.services.AccountService
import pl.nepapp.rasoth.data.services.LoginApiResponse
import pl.nepapp.rasoth.data.services.PasswordLoginApiRequest
import pl.nepapp.rasoth.data.services.SocialLoginApiRequest
import pl.nepapp.rasoth.features.login.AuthTokens
import pl.nepapp.rasoth.features.login.LoginRequest

@Single
class AccountRepository(
    private val accountService: AccountService,
) {
    suspend fun login(request: LoginRequest): AuthTokens {
        val response: LoginApiResponse = when (request) {
            is LoginRequest.Password -> accountService.loginWithPassword(
                PasswordLoginApiRequest(
                    email = request.email,
                    password = request.password,
                )
            ).body()

            is LoginRequest.Social -> accountService.loginWithSocial(
                SocialLoginApiRequest(
                    provider = request.provider.apiValue,
                    firebaseIdToken = request.firebaseIdToken,
                    firebaseAccessToken = request.firebaseAccessToken,
                )
            ).body()
        }

        return AuthTokens(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
        )
    }
}