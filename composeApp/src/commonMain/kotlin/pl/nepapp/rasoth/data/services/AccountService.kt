package pl.nepapp.rasoth.data.services

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Provided

@Provided
interface AccountService {

    @POST("login")
    suspend fun loginWithPassword(
        @Body request: PasswordLoginApiRequest,
    ): HttpResponse

    @POST("login")
    suspend fun loginWithSocial(
        @Body request: SocialLoginApiRequest,
    ): HttpResponse

    @POST("refresh-token")
    suspend fun refreshToken(
        @Body request: RefreshTokenApiRequest,
    ): HttpResponse
}

@Serializable
data class PasswordLoginApiRequest(
    val email: String,
    val password: String,
)

@Serializable
data class SocialLoginApiRequest(
    val provider: String,
    @SerialName("firebaseIdToken")
    val firebaseIdToken: String,
    @SerialName("firebaseAccessToken")
    val firebaseAccessToken: String? = null,
)

@Serializable
data class RefreshTokenApiRequest(
    @SerialName("refreshToken")
    val refreshToken: String,
)

@Serializable
data class LoginApiResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
)
