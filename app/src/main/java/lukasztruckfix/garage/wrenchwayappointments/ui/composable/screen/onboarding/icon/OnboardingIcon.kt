package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

private val CompositionSize = 168.dp
private val OrbitRadius = 58.dp

private val CenterChipSize = 72.dp
private val CenterIconSize = 32.dp
private val SatelliteIconSize = 16.dp

private data class SatelliteLayout(
    val angleDegrees: Float,
    val radiusFactor: Float,
    val chipSize: Dp,
)

private val SatelliteLayouts = listOf(
    SatelliteLayout(angleDegrees = -100f, radiusFactor = 1.05f, chipSize = 34.dp),
    SatelliteLayout(angleDegrees = 5f, radiusFactor = 0.95f, chipSize = 28.dp),
    SatelliteLayout(angleDegrees = 90f, radiusFactor = 1.0f, chipSize = 36.dp),
    SatelliteLayout(angleDegrees = 185f, radiusFactor = 0.92f, chipSize = 24.dp),
)

@Composable
fun OnboardingIcon(
    icons: List<ImageVector>,
    modifier: Modifier = Modifier,
) {
    val centerIcon = icons.first()
    val satelliteIcons = List(SatelliteLayouts.size) { index -> icons[(index + 1) % icons.size] }

    Box(
        modifier = modifier.size(CompositionSize),
        contentAlignment = Alignment.Center,
    ) {
        satelliteIcons.forEachIndexed { index, icon ->
            val layout = SatelliteLayouts[index]
            val angle = Math.toRadians(layout.angleDegrees.toDouble())
            val radius = OrbitRadius.value * layout.radiusFactor
            val offsetX = (radius * cos(angle)).toFloat().dp
            val offsetY = (radius * sin(angle)).toFloat().dp

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .size(layout.chipSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(SatelliteIconSize),
                )
            }
        }

        Box(
            modifier = Modifier
                .size(CenterChipSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = centerIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(CenterIconSize),
            )
        }
    }
}
