package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.LocalContentBottomPadding
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.settings.card.AppInfoCard
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens

@Composable
fun AJTHSettingsScreen(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.screenPadding)
            .padding(bottom = LocalContentBottomPadding.current),
    ) {
        AppInfoCard()
    }
}
