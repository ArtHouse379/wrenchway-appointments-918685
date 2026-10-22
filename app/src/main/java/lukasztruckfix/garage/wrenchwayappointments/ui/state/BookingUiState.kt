package lukasztruckfix.garage.wrenchwayappointments.ui.state

data class BookingUiState(
    val serviceName: String,
    val bookingNumber: String,
    val customerFirstName: String,
    val customerLastName: String,
    val customerEmail: String,
    val timestamp: String,
)