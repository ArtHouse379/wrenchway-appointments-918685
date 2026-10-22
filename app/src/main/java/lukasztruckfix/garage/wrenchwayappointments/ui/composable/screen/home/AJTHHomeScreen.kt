package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceCategory
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceModel
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.LocalContentBottomPadding
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.home.card.ServiceItem
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.home.filter.CategoryFilterRow
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.home.slider.FeaturedSlider
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataBasedContainer
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.DataEmptyContent
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.sectiontitle.SectionTitle
import lukasztruckfix.garage.wrenchwayappointments.ui.state.DataUiState
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

private const val FeaturedCount = 3

@Composable
fun AJTHHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val servicesState by viewModel.servicesState.collectAsState()

    HomeContent(
        servicesState = servicesState,
        modifier = modifier,
        onNavigateToServiceDetails = onNavigateToServiceDetails
    )
}

@Composable
private fun HomeContent(
    servicesState: DataUiState<List<ServiceModel>>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        DataBasedContainer(
            dataState = servicesState,

            dataPopulated = {
                ServicesPopulated(
                    services = (servicesState as DataUiState.Populated).data,
                    onNavigateToServiceDetails = onNavigateToServiceDetails,
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

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val dimens = LocalDimens.current
    var selectedCategory by remember { mutableStateOf<ServiceCategory?>(null) }

    val featuredServices = remember(services) { services.take(FeaturedCount) }
    val filteredServices = remember(services, selectedCategory) {
        val category = selectedCategory
        if (category == null) services else services.filter { it.serviceCategory == category }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = dimens.screenPadding,
            bottom = dimens.screenPadding + LocalContentBottomPadding.current,
        ),
    ) {
        item {
            SectionTitle(
                text = stringResource(R.string.home_featured_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding),
            )
        }

        item {
            FeaturedSlider(
                services = featuredServices,
                onServiceClick = onNavigateToServiceDetails,
                modifier = Modifier.padding(top = dimens.contentSpacing),
            )
        }

        item {
            SectionTitle(
                text = stringResource(R.string.home_filter_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding)
                    .padding(top = dimens.sectionGap, bottom = dimens.contentSpacing),
            )
        }

        stickyHeader {
            CategoryFilterRow(
                selectedCategory = selectedCategory,
                onCategoryClick = { selectedCategory = it },
            )
        }

        item {
            SectionTitle(
                text = stringResource(R.string.home_services_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding)
                    .padding(top = dimens.sectionGap),
                count = filteredServices.size,
            )
        }

        itemsIndexed(
            items = filteredServices,
            key = { _, service -> service.id }) { index, service ->
            ServiceItem(
                service = service,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding)
                    .padding(top = if (index == 0) dimens.contentSpacing else dimens.itemSpacing),
                onClick = { onNavigateToServiceDetails(service.id) },
            )
        }
    }
}
