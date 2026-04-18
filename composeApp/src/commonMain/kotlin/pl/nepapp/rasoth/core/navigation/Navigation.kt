package pl.nepapp.rasoth.core.navigation

import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavBackStack

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
        if (navigator.size > 1) {
            navigator.removeAt(navigator.size - 1)
        }
    }

    fun popAll() {
        popUntilRoot()
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
        while (navigator.size > 1) {
            navigator.removeAt(navigator.size - 1)
        }
        if (navigator.isNotEmpty()) {
            navigator[0] = item
        } else {
            navigator.add(item)
        }
    }

    fun replaceAll(items: List<BaseScreen>) {
        require(items.isNotEmpty()) { "Cannot replaceAll with empty list" }
        while (navigator.size > 1) {
            navigator.removeAt(navigator.size - 1)
        }
        if (navigator.isNotEmpty()) {
            navigator[0] = items.first()
        } else {
            navigator.add(items.first())
        }
        navigator.addAll(items.drop(1))
    }
}
