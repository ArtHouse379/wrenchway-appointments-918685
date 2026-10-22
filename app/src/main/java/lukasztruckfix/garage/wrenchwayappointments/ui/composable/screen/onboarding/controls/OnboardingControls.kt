package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding.controls

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lukasztruckfix.garage.wrenchwayappointments.R
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens

private val DotWidth = 8.dp
private val SelectedDotWidth = 24.dp
private val DotHeight = 8.dp

@Composable
fun OnboardingControls(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            if (!isLastPage) {
                TextButton(
                    onClick = onSkipClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                ) {
                    Text(text = stringResource(R.string.skip_button_title))
                }
            }
        }

        OnboardingWormIndicator(
            pageCount = pageCount,
            currentPage = currentPage,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = dimens.sectionGap),
        )

        Button(
            onClick = onNextClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text(
                text = stringResource(
                    if (isLastPage) R.string.start_button_title else R.string.next_button_title
                )
            )
        }
    }
}

@Composable
private fun OnboardingWormIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        repeat(pageCount) { page ->
            val isSelected = currentPage == page

            val width by animateDpAsState(
                targetValue = if (isSelected) SelectedDotWidth else DotWidth,
                label = "onboarding_worm_dot_width",
            )
            val color by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                },
                label = "onboarding_worm_dot_color",
            )

            Box(
                modifier = Modifier
                    .height(DotHeight)
                    .width(width)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
