package com.example.radiusassigment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.radiusassigment.model.Exclusion
import com.example.radiusassigment.model.Facility
import com.example.radiusassigment.model.ModelClass
import com.example.radiusassigment.model.Option

@Database(entities = [ModelClass::class,Exclusion::class,Facility::class,Option::class], version = 1)
@TypeConverters(ExclusionListTypeConverter::class,FacilityListTypeConverter::class,OptionListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao?

    companion object {
        private const val DATABASE_NAME = "properties_database.db"

        @Volatile
        var instance: AppDatabase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return instance
        }
    }
}