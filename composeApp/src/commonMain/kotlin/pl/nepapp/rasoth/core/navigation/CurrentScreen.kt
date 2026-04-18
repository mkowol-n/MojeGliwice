package pl.nepapp.rasoth.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.NavDisplay

@Composable
fun CurrentScreen(
    navigation: Navigation,
    transitionSpec: AnimatedContentTransitionScope<Scene<BaseScreen>>.() -> ContentTransform = {
        navigationSlideTransitionSpec
    },
    popTransitionSpec: AnimatedContentTransitionScope<Scene<BaseScreen>>.() -> ContentTransform = {
        navigationSlideTransitionSpecPop
    },
) {
    NavDisplay(
        backStack = navigation.backStack,
        onBack = navigation::pop,
        transitionSpec = transitionSpec,
        popTransitionSpec = popTransitionSpec,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            NavEntry(key) {
                it.Content()
            }
        },
    )
}
