package pl.nepapp.rasoth.core.navigation

import androidx.savedstate.serialization.SavedStateConfiguration
import pl.nepapp.rasoth.core.serialization.LocalJson

val savedState = SavedStateConfiguration {
    serializersModule = LocalJson.serializersModule
}
