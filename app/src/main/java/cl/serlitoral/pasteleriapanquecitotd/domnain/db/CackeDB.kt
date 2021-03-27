package cl.serlitoral.pasteleriapanquecitotd.domnain.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CakeEntity::class, CakeDetailEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CakeDatabase: RoomDatabase() {
    abstract fun cakeDao(): CakeDAO
}

class CakeApplication: Application() {
    companion object {
        var db: CakeDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(this, CakeDatabase::class.java, "cake_db")
            .build()
    }
}