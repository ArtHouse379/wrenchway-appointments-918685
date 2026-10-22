package lukasztruckfix.garage.wrenchwayappointments.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("bookings")
data class BookingEntity(
    @PrimaryKey @ColumnInfo(name = "booking_number") val bookingNumber: String,
    val serviceId: Int,
    @ColumnInfo(name = "customer_first_name") val customerFirstName: String,
    @ColumnInfo(name = "customer_last_name") val customerLastName: String,
    @ColumnInfo(name = "customer_email") val customerEmail: String,
    val timestamp: LocalDateTime,
)