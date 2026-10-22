package lukasztruckfix.garage.wrenchwayappointments.data.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TireRepair
import androidx.compose.ui.graphics.vector.ImageVector
import lukasztruckfix.garage.wrenchwayappointments.R

enum class ServiceCategory(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
) {
    ROUTINE_MAINTENANCE(R.string.service_category_routine_maintenance_title, Icons.Default.Build),
    DIAGNOSTICS(R.string.service_category_diagnostics_title, Icons.Default.Search),
    MECHANICAL_REPAIR(R.string.service_category_mechanical_repair_title, Icons.Default.CarRepair),
    TYRES_BRAKES(R.string.service_category_tyres_brakes_title, Icons.Default.TireRepair),
    PARTS_ACCESSORIES(R.string.service_category_parts_accessories_title, Icons.Default.ShoppingCart),
}
