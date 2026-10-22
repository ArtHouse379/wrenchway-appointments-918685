package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceModel
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.servicedetails.layout.ServiceDetailsLayout
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataBasedContainer
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataEmptyContent
import lukasztruckfix.garage.wrenchwayappointments.ui.state.DataUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AJTHServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val serviceState by viewModel.serviceState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeServiceById(serviceId)
    }

    ServiceDetailsContent(
        serviceState = serviceState,
        modifier = modifier,
        onNavigateToCheckout = onNavigateToCheckout
    )
}

@Composable
private fun ServiceDetailsContent(
    serviceState: DataUiState<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        DataBasedContainer(
            dataState = serviceState,

            dataPopulated = {
                ServiceDetailsLayout(
                    service = (serviceState as DataUiState.Populated).data,
                    modifier = Modifier.fillMaxSize(),
                    onNavigateToCheckout = onNavigateToCheckout,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
