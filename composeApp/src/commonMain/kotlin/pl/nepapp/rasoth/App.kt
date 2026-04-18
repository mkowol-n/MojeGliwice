package pl.nepapp.rasoth

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import pl.nepapp.rasoth.core.navigation.NavigationRoot
import pl.nepapp.rasoth.features.login.LoginScreen

@Composable
fun App() {
    MaterialTheme {
        NavigationRoot(LoginScreen)
    }
}
