package pl.nepapp.rasoth.core.auth

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.annotation.Single

@Single
class FirebaseAuthClientImpl: FirebaseAuthClient {

    override suspend fun signInWithEmail(
        email: String,
        password: String,
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password
        )
    }
}
