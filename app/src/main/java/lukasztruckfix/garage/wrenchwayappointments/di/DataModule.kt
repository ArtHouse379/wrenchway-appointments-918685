package lukasztruckfix.garage.wrenchwayappointments.di

import lukasztruckfix.garage.wrenchwayappointments.data.repository.BookingRepository
import lukasztruckfix.garage.wrenchwayappointments.data.repository.OnboardingRepository
import lukasztruckfix.garage.wrenchwayappointments.data.repository.ServiceRepository
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { ServiceRepository() }

    single {
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}