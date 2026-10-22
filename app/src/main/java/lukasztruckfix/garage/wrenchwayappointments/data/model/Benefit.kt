package lukasztruckfix.garage.wrenchwayappointments.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

data class Benefit(
    val icon: ImageVector,
    val text: String,
)

val defaultServiceBenefits: List<Benefit> = listOf(
    Benefit(icon = Icons.Default.BuildCircle, text = "Workshop-grade workmanship"),
    Benefit(icon = Icons.Default.VerifiedUser, text = "Repair warranty included"),
    Benefit(icon = Icons.Default.HealthAndSafety, text = "Essential road safety"),
)
