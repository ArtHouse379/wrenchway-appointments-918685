package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.sectiontitle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens

private val StripeWidth = 4.dp
private val CornerLegLength = 24.dp

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
    count: Int? = null,
) {
    val dimens = LocalDimens.current
    val stripeColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Canvas(modifier = Modifier.matchParentSize()) {
                val strokeWidthPx = StripeWidth.toPx()
                val cornerLegPx = CornerLegLength.toPx()
                val cornerY = size.height - strokeWidthPx / 2f

                drawLine(
                    color = stripeColor,
                    start = Offset(x = strokeWidthPx / 2f, y = 0f),
                    end = Offset(x = strokeWidthPx / 2f, y = cornerY),
                    strokeWidth = strokeWidthPx,
                    cap = StrokeCap.Round,
                )

                drawLine(
                    color = stripeColor,
                    start = Offset(x = strokeWidthPx / 2f, y = cornerY),
                    end = Offset(x = cornerLegPx, y = cornerY),
                    strokeWidth = strokeWidthPx,
                    cap = StrokeCap.Round,
                )
            }

            Text(
                text = text,
                modifier = Modifier.padding(
                    start = StripeWidth + dimens.itemSpacing,
                    bottom = dimens.tightSpacing,
                ),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        if (count != null) {
            Text(
                text = stringResource(R.string.home_services_count, count),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
