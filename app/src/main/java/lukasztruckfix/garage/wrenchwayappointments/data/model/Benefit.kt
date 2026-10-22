package lukasztruckfix.garage.wrenchwayappointments.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Benefit(
    val icon: ImageVector,
    val text: String,
)

val defaultServiceBenefits: List<Benefit> = listOf(

)