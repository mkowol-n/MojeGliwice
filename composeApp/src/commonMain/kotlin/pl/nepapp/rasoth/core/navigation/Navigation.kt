package pl.nepapp.rasoth.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.collections.immutable.ImmutableList
import pl.nepapp.rasoth.core.navigation.BaseScreen

@Stable
class Navigation(
    private val mparent: Navigation?,
    private val navigator: NavBackStack<BaseScreen>
) {

    val backStack: List<BaseScreen>
        get() = navigator

    val parent: Navigation
        get() = requireNotNull(mparent) {
            "Root navigation does not have a parent"
        }

    fun pop() {
        if (navigator.isNotEmpty()) {
            navigator.removeAt(navigator.size - 1)
        }
    }

    fun popAll() {
        navigator.clear()
    }

    fun popUntil(predicate: (BaseScreen) -> Boolean): Boolean {
        var index = -1

        for (i in navigator.indices.reversed()) {
            if (predicate(navigator[i])) {
                index = i
                break
            }
        }

        if (index == -1) return false

        while (navigator.size - 1 > index) {
            navigator.removeAt(navigator.size - 1)
        }

        return true
    }

    fun popUntilRoot() {
        if (navigator.size <= 1) return

        val root = navigator[0]
        navigator.clear()
        navigator.add(root)
    }

    fun push(item: BaseScreen) {
        navigator.add(item)
    }

    fun push(items: List<BaseScreen>) {
        navigator.addAll(items)
    }

    fun replace(item: BaseScreen) {
        if (navigator.isNotEmpty()) {
            navigator.removeAt(navigator.size - 1)
        }
        navigator.add(item)
    }

    fun replaceAll(item: BaseScreen) {
        navigator.clear()
        navigator.add(item)
    }

    fun replaceAll(items: List<BaseScreen>) {
        navigator.clear()
        navigator.addAll(items)
    }
}
