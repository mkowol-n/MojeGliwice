package pl.nepapp.rasoth.core.navigation

import androidx.compose.runtime.compositionLocalOf

val LocalNavigator =
    compositionLocalOf<Navigation?> {
        null
    }
