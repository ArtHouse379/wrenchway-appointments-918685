package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.LocalContentBottomPadding
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.bookings.card.BookingCard
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.bookings.dialog.CancelBookingDialog
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataBasedContainer
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataEmptyContent
import lukasztruckfix.garage.wrenchwayappointments.ui.state.BookingUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.state.DataUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AJTHBookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel()
) {
    val bookingsState by viewModel.bookingsState.collectAsState()

    var canceledBookingNumber by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    BookingsContent(
        bookingsState = bookingsState,
        modifier = modifier,
        onCancelBookingButtonClick = { bookingNumber ->
            canceledBookingNumber = bookingNumber
            shouldShowDialog = true
        }
    )

    if (shouldShowDialog) {
        CancelBookingDialog(
            onConfirm = {
                viewModel.cancelBooking(canceledBookingNumber)
                shouldShowDialog = false
            },
            onDismiss = { shouldShowDialog = false },
        )
    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    Column(modifier = modifier) {

        DataBasedContainer(
            dataState = bookingsState,

            dataPopulated = {
                BookingsPopulated(
                    bookings = (bookingsState as DataUiState.Populated).data,
                    onCancelBookingButtonClick = onCancelBookingButtonClick,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.bookings_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    val dimens = LocalDimens.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = dimens.screenPadding,
            end = dimens.screenPadding,
            top = dimens.screenPadding,
            bottom = dimens.screenPadding + LocalContentBottomPadding.current,
        ),
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        items(items = bookings, key = { it.bookingNumber }) { booking ->
            BookingCard(
                booking = booking,
                modifier = Modifier.fillMaxWidth(),
                onCancelButtonClick = { onCancelBookingButtonClick(booking.bookingNumber) },
            )
        }
    }
}
