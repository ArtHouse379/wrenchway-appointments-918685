package lukasztruckfix.garage.wrenchwayappointments.di

import lukasztruckfix.garage.wrenchwayappointments.data.datastore.OnboardingDataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { OnboardingDataStoreManager(androidContext()) }
}