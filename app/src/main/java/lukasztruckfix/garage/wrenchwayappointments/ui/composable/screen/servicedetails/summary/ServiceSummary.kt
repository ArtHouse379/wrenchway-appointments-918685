package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.servicedetails.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceModel
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.controlBorderStroke

@Composable
fun ServiceSummary(
    service: ServiceModel,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Surface(
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            border = controlBorderStroke(color = MaterialTheme.colorScheme.onSecondaryContainer),
        ) {
            Text(
                text = stringResource(service.serviceCategory.titleRes),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelMedium,
            )
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = service.name,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = stringResource(R.string.price_per_hour, service.pricePerHour),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
