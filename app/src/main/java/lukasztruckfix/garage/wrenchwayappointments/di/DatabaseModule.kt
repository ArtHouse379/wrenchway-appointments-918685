package lukasztruckfix.garage.wrenchwayappointments.di

import androidx.room.Room
import lukasztruckfix.garage.wrenchwayappointments.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "ajth_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao() }
}