package pl.nepapp.rasoth.core.auth

interface FirebaseAuthClient {
    suspend fun signInWithEmail(
        email: String,
        password: String,
    )
}