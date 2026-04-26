package pl.nepapp.rasoth.core.auth

data class SocialAuthTokens(
    val firebaseIdToken: String,
    val firebaseAccessToken: String? = null,
)