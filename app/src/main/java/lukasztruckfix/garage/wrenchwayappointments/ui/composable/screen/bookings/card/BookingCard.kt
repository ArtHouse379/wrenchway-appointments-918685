package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.bookings.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.ui.state.BookingUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.ControlDivider
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.cardBorderStroke
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.controlBorderStroke

@Composable
fun BookingCard(
    booking: BookingUiState,
    modifier: Modifier = Modifier,
    onCancelButtonClick: () -> Unit,
) {
    val dimens = LocalDimens.current

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = cardBorderStroke(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.cardPadding),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = booking.serviceName,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.width(dimens.itemSpacing))

                Surface(
                    shape = RoundedCornerShape(dimens.controlCornerRadius),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    border = controlBorderStroke(color = MaterialTheme.colorScheme.onTertiaryContainer),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = stringResource(R.string.booking_status_confirmed),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            ControlDivider()

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            InfoRow(
                icon = Icons.Filled.ConfirmationNumber,
                text = stringResource(R.string.booking_number, booking.bookingNumber),
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            InfoRow(
                icon = Icons.Filled.Person,
                text = stringResource(
                    R.string.booking_customer,
                    booking.customerFirstName,
                    booking.customerLastName,
                ),
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            InfoRow(
                icon = Icons.Filled.Email,
                text = stringResource(R.string.booking_customer_email, booking.customerEmail),
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            InfoRow(
                icon = Icons.Filled.Schedule,
                text = booking.timestamp,
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            TextButton(
                onClick = onCancelButtonClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error,
                ),
            ) {
                Text(text = stringResource(R.string.button_cancel_booking_text))
            }
        }
    }
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp),
        )

        Spacer(modifier = Modifier.width(LocalDimens.current.itemSpacing))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
