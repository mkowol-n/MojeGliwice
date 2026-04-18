package pl.nepapp.rasoth.core.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun LocalDateTime.toCurrentTimeZoneInstant(): Instant {
    return this.toInstant(TimeZone.currentSystemDefault())
}

fun Instant.toCurrentTimeZoneLocalDateTime(): LocalDateTime {
    return this.toLocalDateTime(TimeZone.currentSystemDefault())
}
