package pl.nepapp.rasoth.core.auth

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import kotlinx.coroutines.tasks.await
import org.jetbrains.compose.resources.getString
import pl.nepapp.rasoth.features.login.SocialProvider
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.firebase_auth_base_error

private class AndroidSocialAuthClient(private val context: Context) : SocialAuthClient {
    override suspend fun signInWithProvider(provider: SocialProvider): SocialAuthTokens? {
        val auth = FirebaseAuth.getInstance()
        val authResult = auth.pendingAuthResult?.await() ?: auth.startActivityForSignInWithProvider(
            context as ComponentActivity,
            OAuthProvider.newBuilder(provider.firebaseProvider).build(),
        ).await()

        val user = authResult.user

        if (user == null) {
            Toast.makeText(
                context,
                getString(Res.string.firebase_auth_base_error),
                Toast.LENGTH_SHORT
            ).show()
            return null
        }

        val firebaseIdToken = runCatching {
            user.getIdToken(true).await().token
        }.getOrNull()

        if (firebaseIdToken == null) {
            Toast.makeText(
                context,
                getString(Res.string.firebase_auth_base_error),
                Toast.LENGTH_SHORT
            ).show()
            return null
        }

        val firebaseAccessToken = (authResult.credential as? OAuthCredential)?.accessToken
        return SocialAuthTokens(
            firebaseIdToken = firebaseIdToken,
            firebaseAccessToken = firebaseAccessToken,
        )
    }
}

@Composable
actual fun rememberSocialAuthClient(): SocialAuthClient {
    val context = LocalContext.current
    return remember {
        AndroidSocialAuthClient(context)
    }
}
