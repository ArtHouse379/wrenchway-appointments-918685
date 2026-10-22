package lukasztruckfix.garage.wrenchwayappointments

import android.app.Application
import lukasztruckfix.garage.wrenchwayappointments.di.dataModule
import lukasztruckfix.garage.wrenchwayappointments.di.dispatcherModule
import lukasztruckfix.garage.wrenchwayappointments.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AJTHApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@AJTHApplication)
            modules(appModules)
        }
    }
}
