package lukasztruckfix.garage.wrenchwayappointments.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

enum class ServiceCategory(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
) {

}
