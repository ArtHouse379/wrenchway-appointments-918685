package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.data.model.BookingWindow
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

/**
 * About two rows of hour chips — enough to see the shape of the choice
 * without guessing, the rest scrolls into view.
 */
private val TimeSlotAreaMaxHeight = 112.dp

/**
 * Date and hour-slot picker in one dialog, not two: a separate date step
 * followed by a separate time step would let the user forget the second
 * step existed, and this booking's date/time is a single required fact,
 * not two.
 *
 * Hours are a fixed row of slots (8:00–16:00), not `TimePicker`'s free-form
 * clock — the business only takes sessions on the hour within that window,
 * and a free-form clock would let the user dial in a time that's just going
 * to get rejected. Slots that fall in the past for the selected date (only
 * possible when that date is today) are shown but disabled, not hidden —
 * hiding them would make the row jump around as the day goes on.
 *
 * Built on a plain `Dialog`, not `AlertDialog`: `DatePicker` wants to size
 * itself to its own natural width, and `AlertDialog`'s content slot fights
 * that. This is the same shell `DatePickerDialog` uses internally.
 *
 * `DatePicker` is never height-constrained or put inside a scroll — it's
 * left to render at its own full natural size, both because it looks wrong
 * (squished, oval day cells instead of circles) the moment it's forced
 * into less room than it wants, and because Material3's calendar may use
 * an internally-lazy layout that can crash if nested in an outer scroll
 * expecting a bounded height. Only the hour-slot row gets a fixed, modest
 * height cap ([TimeSlotAreaMaxHeight]) with its own independent scroll —
 * a small, self-contained island rather than something squeezed out of
 * whatever height the calendar left behind.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDateTimePickerDialog(
    initialDateTime: LocalDateTime?,
    onConfirm: (LocalDateTime) -> Unit,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current
    val today = remember { LocalDate.now() }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = (initialDateTime?.toLocalDate() ?: today)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli(),
        selectableDates = remember {
            object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val date = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneOffset.UTC).toLocalDate()
                    return !date.isBefore(today)
                }
            }
        },
    )

    var selectedHour by remember { mutableStateOf(initialDateTime?.hour) }

    val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
        Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
    }

    // Switching to a date that makes the previously-picked hour invalid
    // (e.g. it was valid for "today" and is now in the past) drops it,
    // rather than silently keeping a slot that's no longer selectable.
    LaunchedEffect(selectedDate) {
        val date = selectedDate ?: return@LaunchedEffect
        val hour = selectedHour ?: return@LaunchedEffect
        if (!BookingWindow.isValid(LocalDateTime.of(date, LocalTime.of(hour, 0)))) {
            selectedHour = null
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(bottom = dimens.contentSpacing)) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                )

                Column(
                    modifier = Modifier
                        .heightIn(max = TimeSlotAreaMaxHeight)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = stringResource(R.string.checkout_date_time_dialog_time_label),
                        modifier = Modifier.padding(horizontal = dimens.cardPadding),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall,
                    )

                    Spacer(modifier = Modifier.height(dimens.tightSpacing))

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimens.cardPadding),
                        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
                        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
                    ) {
                        (BookingWindow.START_HOUR..BookingWindow.END_HOUR).forEach { hour ->
                            val slotEnabled = selectedDate != null &&
                                BookingWindow.isValid(LocalDateTime.of(selectedDate, LocalTime.of(hour, 0)))

                            val isSelected = selectedHour == hour

                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedHour = hour },
                                enabled = slotEnabled,
                                label = {
                                    Text(
                                        text = "%02d:00".format(hour),
                                        style = MaterialTheme.typography.labelSmall,
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true,
                                    selected = isSelected,
                                    borderColor = MaterialTheme.colorScheme.outlineVariant,
                                    selectedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                    borderWidth = dimens.controlBorderWidth,
                                    selectedBorderWidth = dimens.controlBorderWidth * 2,
                                ),
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimens.contentSpacing, end = dimens.cardPadding),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(R.string.checkout_date_time_dialog_cancel),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    TextButton(
                        onClick = {
                            val date = selectedDate
                            val hour = selectedHour
                            if (date != null && hour != null) {
                                onConfirm(LocalDateTime.of(date, LocalTime.of(hour, 0)))
                            }
                        },
                        enabled = selectedDate != null && selectedHour != null,
                    ) {
                        Text(
                            text = stringResource(R.string.checkout_date_time_dialog_confirm),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
        }
    }
}
