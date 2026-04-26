package pl.nepapp.rasoth.core.auth

import androidx.compose.runtime.Composable
import pl.nepapp.rasoth.features.login.SocialProvider

interface SocialAuthClient {
    suspend fun signInWithProvider(provider: SocialProvider): SocialAuthTokens?
}

@Composable
expect fun rememberSocialAuthClient(): SocialAuthClient