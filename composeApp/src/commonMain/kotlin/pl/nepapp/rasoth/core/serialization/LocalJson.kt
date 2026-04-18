package pl.nepapp.rasoth.core.serialization

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import pl.nepapp.rasoth.shared.generated.registerNavKeys

val LocalJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true

    serializersModule = SerializersModule {
        contextual(LocalDateTime::class, LocalDateTimeSerializer)
        registerNavKeys()
    }
}
