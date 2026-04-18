package pl.nepapp.rasoth.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavKey

@Stable
interface BaseScreen: NavKey {
    @Composable
    fun Content()
}