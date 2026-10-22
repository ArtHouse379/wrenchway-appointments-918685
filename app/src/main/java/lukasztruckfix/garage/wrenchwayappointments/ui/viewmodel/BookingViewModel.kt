package lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import lukasztruckfix.garage.wrenchwayappointments.data.repository.BookingRepository
import lukasztruckfix.garage.wrenchwayappointments.data.repository.ServiceRepository
import lukasztruckfix.garage.wrenchwayappointments.ui.state.BookingUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.util.Locale

class BookingViewModel(
    private val bookingRepository: BookingRepository,
    private val serviceRepository: ServiceRepository,
) : ViewModel() {
    // Локаль задана явно: без неё `ofPattern` берёт системную локаль
    // устройства, а весь остальной текст приложения — английский вне
    // зависимости от настроек пользователя.
    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)

    private val _bookingsState =
        MutableStateFlow<DataUiState<List<BookingUiState>>>(DataUiState.Initial)
    val bookingsState: StateFlow<DataUiState<List<BookingUiState>>>
        get() = _bookingsState.asStateFlow()

    init {
        observeBookings()
    }

    private fun observeBookings() {
        viewModelScope.launch {
            bookingRepository.observeAll().collect { bookings ->
                val mapped = bookings.mapNotNull { booking ->
                    serviceRepository.getById(booking.serviceId)?.let { service ->
                        BookingUiState(
                            serviceName = service.name,
                            bookingNumber = booking.bookingNumber,
                            customerFirstName = booking.customerFirstName,
                            customerLastName = booking.customerLastName,
                            customerEmail = booking.customerEmail,
                            timestamp = booking.timestamp.format(formatter)
                        )
                    }
                }

                _bookingsState.update {
                    DataUiState.from(mapped)
                }
            }
        }
    }

    fun cancelBooking(bookingNumber: String){
        viewModelScope.launch {
            bookingRepository.deleteByBookingNumber(bookingNumber)
        }
    }
}