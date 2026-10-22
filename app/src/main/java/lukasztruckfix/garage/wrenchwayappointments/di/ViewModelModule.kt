package lukasztruckfix.garage.wrenchwayappointments.di

import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.BookingViewModel
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.CheckoutViewModel
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.OnboardingViewModel
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.ServiceDetailsViewModel
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.ServiceViewModel
import lukasztruckfix.garage.wrenchwayappointments.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}