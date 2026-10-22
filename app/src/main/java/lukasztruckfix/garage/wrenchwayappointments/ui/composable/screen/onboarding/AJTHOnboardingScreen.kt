package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.LocalContentBottomPadding
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding.controls.OnboardingControls
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding.icon.OnboardingIcon
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding.text.OnboardingText
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.LocalDimens
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AJTHOnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    OnboardingPager(
        modifier = modifier,
        onFinishClick = viewModel::setOnboarded,
    )
}

@Composable
private fun OnboardingPager(
    modifier: Modifier = Modifier,
    onFinishClick: () -> Unit,
) {
    val dimens = LocalDimens.current
    val pagerState = rememberPagerState { onboardingPagesContent.size }
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == onboardingPagesContent.lastIndex

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = dimens.screenPadding,
                top = dimens.screenPadding,
                end = dimens.screenPadding,
                // The screen has neither its own bottom bar nor a shared one
                // (bottomBarHiddenScreens), so the button must be offset
                // from the system navigation bar manually.
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) { page ->
            OnboardingPage(content = onboardingPagesContent[page])
        }

        OnboardingControls(
            pageCount = onboardingPagesContent.size,
            currentPage = pagerState.currentPage,
            isLastPage = isLastPage,
            onSkipClick = onFinishClick,
            onNextClick = {
                if (isLastPage) {
                    onFinishClick()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
        )
    }
}

@Composable
private fun OnboardingPage(
    content: OnboardingContent,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OnboardingIcon(icons = content.icons)

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        OnboardingText(
            title = stringResource(content.titleRes),
            description = stringResource(content.descriptionRes),
        )
    }
}
