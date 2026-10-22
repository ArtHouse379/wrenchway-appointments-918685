package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lukasztruckfix.garage.wrenchwayappointments.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)

@Composable
fun BookingDateTimeField(
    selectedDateTime: LocalDateTime?,
    modifier: Modifier = Modifier,
    onDateTimeSelected: (LocalDateTime) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        if (isPressed) {
            showDialog = true
        }
    }

    OutlinedTextField(
        value = selectedDateTime?.format(displayFormatter).orEmpty(),
        onValueChange = {},
        modifier = modifier.fillMaxWidth(),
        readOnly = true,
        interactionSource = interactionSource,
        label = {
            Text(
                text = stringResource(R.string.checkout_text_field_date_time),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.checkout_date_time_placeholder))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null)
        },
        singleLine = true,
    )

    if (showDialog) {
        BookingDateTimePickerDialog(
            initialDateTime = selectedDateTime,
            onConfirm = { dateTime ->
                showDialog = false
                onDateTimeSelected(dateTime)
            },
            onDismiss = { showDialog = false },
        )
    }
}
