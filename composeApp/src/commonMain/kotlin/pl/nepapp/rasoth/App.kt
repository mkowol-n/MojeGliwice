package pl.nepapp.rasoth

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import pl.nepapp.rasoth.core.navigation.NavigationRoot
import pl.nepapp.rasoth.features.login.LoginScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationRoot(LoginScreen)
    }
}