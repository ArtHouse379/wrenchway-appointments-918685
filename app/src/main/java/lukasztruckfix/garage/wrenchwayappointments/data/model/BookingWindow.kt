package lukasztruckfix.garage.wrenchwayappointments.data.model

import java.time.LocalDateTime

/**
 * When a booking is allowed to start: not in the past, and only within the
 * hours the consultancy actually takes sessions. Shared by the date/time
 * picker (to grey out invalid slots) and `CheckoutViewModel` (to re-validate
 * before writing a booking, the same way email format is re-validated there
 * rather than trusted from the UI alone).
 */
object BookingWindow {
    const val START_HOUR = 8
    const val END_HOUR = 16

    fun isValid(dateTime: LocalDateTime): Boolean {
        return dateTime.isAfter(LocalDateTime.now()) && dateTime.hour in START_HOUR..END_HOUR
    }
}
