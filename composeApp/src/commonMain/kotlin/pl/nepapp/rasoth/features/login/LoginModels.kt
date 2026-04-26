package pl.nepapp.rasoth.features.login

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String,
)

sealed interface LoginRequest {
    data class Password(
        val email: String,
        val password: String,
    ) : LoginRequest

    data class Social(
        val provider: SocialProvider,
        val firebaseIdToken: String,
        val firebaseAccessToken: String? = null,
    ) : LoginRequest
}

enum class SocialProvider(val apiValue: String, val firebaseProvider: String) {
    GOOGLE("google", firebaseProvider = "google.com"),
    FACEBOOK("facebook", firebaseProvider = "facebook.com"),
    APPLE_ID("appleid", firebaseProvider = "apple.com"),
}
