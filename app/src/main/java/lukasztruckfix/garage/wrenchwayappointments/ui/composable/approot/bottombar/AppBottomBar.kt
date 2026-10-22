package lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.bottombar.BottomNavItem
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.bottombar.isSelectedDestination
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.ControlDivider

@Composable
fun AppBottomBar(
    currentDestination: NavDestination?,
    navigationItems: List<BottomNavItem>,
    onNavigateToRoute: (BottomNavItem) -> Unit,
) {
    Column {
        ControlDivider()

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ) {
            navigationItems.forEach { item ->
                NavigationBarItem(
                    selected = isSelectedDestination(currentDestination, item.route),
                    onClick = { onNavigateToRoute(item) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.titleRes),
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    label = {
                        Text(text = stringResource(item.titleRes))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                    )
                )
            }
        }
    }
}
