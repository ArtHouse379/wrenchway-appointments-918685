package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.splash.SplashContent
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AJTHSplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboardedState by viewModel.onboardedState.collectAsStateWithLifecycle()

    var isAnimationFinished by remember { mutableStateOf(false) }

    LaunchedEffect(isAnimationFinished, onboardedState) {
        val isOnboarded = onboardedState
        if (isAnimationFinished && isOnboarded != null) {
            if (isOnboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
        }
    }

    SplashContent(
        modifier = modifier,
        onAnimationFinished = { isAnimationFinished = true },
    )
}
