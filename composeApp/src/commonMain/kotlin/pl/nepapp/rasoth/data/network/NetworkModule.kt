package pl.nepapp.rasoth.data.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import pl.nepapp.rasoth.core.serialization.LocalJson
import pl.nepapp.rasoth.data.services.AccountService
import pl.nepapp.rasoth.data.services.createAccountService

@Module
class NetworkModule {

    @Single
    fun provideHttpClient(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(LocalJson)
        }
    }.also { client ->
        client.plugin(HttpSend).intercept { request ->
            attachAccessToken(request)

            val originalCall = execute(request)
            if (originalCall.response.status != HttpStatusCode.Unauthorized) {
                return@intercept originalCall
            }

            if (request.url.encodedPath == LOGIN_PATH) {
                return@intercept originalCall
            }

            if (request.url.encodedPath == REFRESH_TOKEN_PATH) {
                return@intercept originalCall
            }

            if (request.headers[AUTH_RETRY_HEADER] == "true") {
                return@intercept originalCall
            }

            // TODO: read refresh token from local storage and invoke AccountService.refreshToken(...)
            // TODO: if refresh succeeds, save new tokens and retry request with updated access token
            request.header(AUTH_RETRY_HEADER, "true")
            execute(request)
        }
    }

    @Single
    fun provideKtorfit(httpClient: HttpClient): Ktorfit = Ktorfit.Builder()
        .baseUrl(API_BASE_URL + "account/")
        .httpClient(httpClient)
        .build()

    @Single
    fun provideAccountService(ktorfit: Ktorfit): AccountService = ktorfit.createAccountService()

    private fun attachAccessToken(request: HttpRequestBuilder) {
        // TODO: read access token from local storage
        val accessToken: String? = null
        accessToken?.let { token ->
            request.header(HttpHeaders.Authorization, "Bearer $token")
        }
    }
}
