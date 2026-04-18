package pl.nepapp.rasoth.core.serialization

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import pl.nepapp.rasoth.core.extensions.toCurrentTimeZoneInstant
import pl.nepapp.rasoth.core.extensions.toCurrentTimeZoneLocalDateTime
import kotlin.time.Instant

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val string = value.toCurrentTimeZoneInstant().toString()
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val string = decoder.decodeString()
        return Instant.parse(string).toCurrentTimeZoneLocalDateTime()
    }
}
