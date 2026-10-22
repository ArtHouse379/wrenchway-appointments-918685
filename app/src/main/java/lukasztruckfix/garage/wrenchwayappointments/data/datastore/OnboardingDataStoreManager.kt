package lukasztruckfix.garage.wrenchwayappointments.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val AJTH_ONBOARDING_PREFS_NAME = "ajth_onboarding_prefs"

val Context.onboardingDataStore by preferencesDataStore(name = AJTH_ONBOARDING_PREFS_NAME)

class OnboardingDataStoreManager(
    private val context: Context
) {
    val onboardedStateFlow: Flow<Boolean?> = context.onboardingDataStore.data.map { prefs ->
        prefs[ONBOARDED_STATE_KEY]
    }

    suspend fun setOnboardedState(state: Boolean) {
        context.onboardingDataStore.edit { prefs ->
            prefs[ONBOARDED_STATE_KEY] = state
        }
    }

    companion object {
        private val ONBOARDED_STATE_KEY = booleanPreferencesKey("onboardedState")
    }
}