package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.home.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceModel
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.shared.ServiceImage
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.cardBorderStroke
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.controlBorderStroke

@Composable
fun FeaturedSlider(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onServiceClick: (serviceId: Int) -> Unit,
) {
    val pagerState = rememberPagerState { services.size }
    val dimens = LocalDimens.current

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimens.screenPadding),
        pageSpacing = dimens.contentSpacing,
    ) { page ->
        val service = services[page]

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { onServiceClick(service.id) },
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            border = cardBorderStroke(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ServiceImage(
                    imageUrl = service.imageUrl,
                    modifier = Modifier.matchParentSize(),
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.scrim.copy(alpha = 0.75f),
                                    Color.Transparent,
                                ),
                            )
                        )
                        .padding(dimens.cardPadding),
                ) {
                    Text(
                        text = service.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = stringResource(R.string.price_per_hour, service.pricePerHour),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(dimens.cardPadding),
                    shape = RoundedCornerShape(dimens.controlCornerRadius),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    border = controlBorderStroke(color = MaterialTheme.colorScheme.onSecondaryContainer),
                ) {
                    Text(
                        text = stringResource(service.serviceCategory.titleRes),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                PagerIndicator(
                    pageCount = services.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(dimens.cardPadding),
                )
            }
        }
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.45f))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { page ->
            val isCurrent = page == currentPage

            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .width(if (isCurrent) 20.dp else 8.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        color = if (isCurrent) Color.White else Color.White.copy(alpha = 0.5f)
                    )
            )
        }
    }
}
