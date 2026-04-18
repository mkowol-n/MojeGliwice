package pl.nepapp.rasoth.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.rememberNavBackStack
import kotlinx.collections.immutable.ImmutableList

@Composable
fun NavigationRoot(
    initialScreen: ImmutableList<BaseScreen>,
    content: @Composable (Navigation) -> Unit = { CurrentScreen(it) }
) {
    NavigationRoot(
        initialScreen = initialScreen.toTypedArray(),
        content = content,
    )
}

@Composable
fun NavigationRoot(
    vararg initialScreen: BaseScreen,
    content: @Composable (Navigation) -> Unit = { CurrentScreen(it) }
) {
    val backStack =
        rememberNavBackStack(elements = initialScreen, configuration = savedState)

    val parent = LocalNavigator.current

    @Suppress("UNCHECKED_CAST")
    val navigator = remember {
        Navigation(parent, backStack as NavBackStack<BaseScreen>)
    }

    CompositionLocalProvider(
        LocalNavigator provides navigator
    ) {
        content(navigator)
    }
}
