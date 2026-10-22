package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import lukasztruckfix.garage.wrenchwayappointments.data.entity.BookingEntity
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.LocalContentBottomPadding
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.checkout.button.CheckoutConfirmButton
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.checkout.form.CheckoutForm
import lukasztruckfix.garage.wrenchwayappointments.ui.state.DataUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime

@Composable
fun AJTHCheckoutScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToBookingsScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val bookingState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalidState by viewModel.emailInvalidState.collectAsStateWithLifecycle()

    val isButtonEnabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotEmpty() &&
                    viewModel.customerLastName.isNotEmpty() &&
                    viewModel.customerEmail.isNotEmpty() &&
                    viewModel.customerDateTime != null
        }
    }

    if (bookingState is DataUiState.Populated) {
        CheckoutDialog(
            booking = (bookingState as DataUiState.Populated<BookingEntity>).data,
            onConfirm = onNavigateToBookingsScreen,
        )
    }

    CheckoutContent(
        customerFirstName = viewModel.customerFirstName,
        customerLastName = viewModel.customerLastName,
        customerEmail = viewModel.customerEmail,
        isEmailInvalid = emailInvalidState,
        selectedDateTime = viewModel.customerDateTime,
        modifier = modifier,
        focusManager = focusManager,
        isButtonEnabled = isButtonEnabled,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onDateTimeSelected = viewModel::updateCustomerDateTime,
        onPlaceBookingButtonClick = { viewModel.placeBooking(serviceId) }
    )
}

@Composable
private fun CheckoutContent(
    customerFirstName: String,
    customerLastName: String,
    customerEmail: String,
    isEmailInvalid: Boolean,
    selectedDateTime: LocalDateTime?,
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    isButtonEnabled: Boolean,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onDateTimeSelected: (LocalDateTime) -> Unit,
    onPlaceBookingButtonClick: () -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimens.screenPadding,
                top = dimens.screenPadding,
                end = dimens.screenPadding,
                // The screen has neither its own bottom bar nor a shared one
                // (bottomBarHiddenScreens), so the button must be offset
                // from the system navigation bar manually.
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
    ) {
        CheckoutForm(
            customerFirstName = customerFirstName,
            customerLastName = customerLastName,
            customerEmail = customerEmail,
            isEmailInvalid = isEmailInvalid,
            selectedDateTime = selectedDateTime,
            modifier = Modifier.weight(1f),
            focusManager = focusManager,
            onFirstNameChanged = onFirstNameChanged,
            onLastNameChanged = onLastNameChanged,
            onEmailChanged = onEmailChanged,
            onDateTimeSelected = onDateTimeSelected,
        )

        HorizontalDivider(
            thickness = dimens.controlBorderWidth.coerceAtLeast(1.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        CheckoutConfirmButton(
            enabled = isButtonEnabled,
            onClick = onPlaceBookingButtonClick,
        )
    }
}