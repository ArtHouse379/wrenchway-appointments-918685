package lukasztruckfix.garage.wrenchwayappointments.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lukasztruckfix.garage.wrenchwayappointments.data.dao.BookingDao
import lukasztruckfix.garage.wrenchwayappointments.data.database.converter.Converters
import lukasztruckfix.garage.wrenchwayappointments.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

