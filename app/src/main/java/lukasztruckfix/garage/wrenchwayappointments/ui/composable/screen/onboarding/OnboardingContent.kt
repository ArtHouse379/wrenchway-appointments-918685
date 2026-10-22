package lukasztruckfix.garage.wrenchwayappointments.ui.composable.screen.onboarding

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector
import lukasztruckfix.garage.wrenchwayappointments.R

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    val icons: List<ImageVector>,
)

val onboardingPagesContent = listOf(
    OnboardingContent(
        titleRes = R.string.ajth_page_expertise_title,
        descriptionRes = R.string.ajth_page_expertise_description,
        icons = listOf(Icons.Filled.WorkspacePremium, Icons.Filled.School),
    ),
    OnboardingContent(
        titleRes = R.string.ajth_page_reliability_title,
        descriptionRes = R.string.ajth_page_reliability_description,
        icons = listOf(Icons.Filled.VerifiedUser, Icons.Filled.Shield, Icons.Filled.Schedule),
    ),
    OnboardingContent(
        titleRes = R.string.ajth_page_guarantee_title,
        descriptionRes = R.string.ajth_page_guarantee_description,
        icons = listOf(Icons.Filled.Policy, Icons.Filled.Handshake, Icons.Filled.CheckCircle),
    ),
)
