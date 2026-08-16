package pl.nepapp.rasoth.features.dashboard

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable
import pl.nepapp.rasoth.core.navigation.BaseScreen
import pl.nepapp.rasoth.core.navigation.NavigationRoot
import pl.nepapp.rasoth.core.ui.RasothScaffold
import pl.nepapp.rasoth.features.Now.NowScreen
import pl.nepapp.rasoth.features.dashboard.components.BottomNavigationItem
import pl.nepapp.rasoth.features.dashboard.components.RasothBottomNavigation

@Serializable
object DashboardScreen: BaseScreen {
    @Composable
    override fun Content() {
        DashboardContent()
    }
}

@Composable
private fun DashboardContent() {
    RasothScaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            RasothBottomNavigation(
                items = persistentListOf(
                    BottomNavigationItem(
                        text = "Hellp",
                        onClick = {}
                    ),
                    BottomNavigationItem(
                        text = "Konto",
                        onClick = {}
                    ),
                )
            )
        }
    ) {
        NavigationRoot(NowScreen)
    }
}