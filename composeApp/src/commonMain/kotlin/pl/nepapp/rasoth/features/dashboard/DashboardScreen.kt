package pl.nepapp.rasoth.features.dashboard

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable
import pl.nepapp.rasoth.core.navigation.BaseScreen
import pl.nepapp.rasoth.core.ui.RasothScaffold
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

    }
}