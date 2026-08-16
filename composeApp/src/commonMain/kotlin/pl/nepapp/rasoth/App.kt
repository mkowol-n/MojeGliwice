package pl.nepapp.rasoth

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import pl.nepapp.rasoth.core.navigation.NavigationRoot
import pl.nepapp.rasoth.core.ui.theme.RasothTheme
import pl.nepapp.rasoth.core.ui.theme.RasothThemeProvider
import pl.nepapp.rasoth.features.dashboard.DashboardScreen
import pl.nepapp.rasoth.features.login.LoginScreen

@Composable
fun App() {
    RasothThemeProvider(
        isDarkTheme = false
    ) {
        NavigationRoot(DashboardScreen)
    }
}
