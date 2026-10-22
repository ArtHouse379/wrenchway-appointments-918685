package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.servicedetails.benefits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.data.model.Benefit
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.controlBorderStroke

private val BenefitTileSize = 100.dp
private val BenefitIconSize = 22.dp
private val BenefitTilePadding = 8.dp

@Composable
fun ServiceBenefitsCard(benefits: List<Benefit>, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current
    val topRow = benefits.take(2)
    val bottomRow = benefits.drop(2)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing)) {
            topRow.forEach { benefit -> BenefitTile(benefit) }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing)) {
            bottomRow.forEach { benefit -> BenefitTile(benefit) }
        }
    }
}

@Composable
private fun BenefitTile(benefit: Benefit) {
    val dimens = LocalDimens.current

    Surface(
        modifier = Modifier.size(BenefitTileSize),
        shape = RoundedCornerShape(dimens.cornerRadius),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        border = controlBorderStroke(color = MaterialTheme.colorScheme.onTertiaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BenefitTilePadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = benefit.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(BenefitIconSize),
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Text(
                text = benefit.text,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
