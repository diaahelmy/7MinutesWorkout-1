package com.example.a7minutesworkout

import android.content.Context
import androidx.room.*

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null
        fun getINSTANCE(context: Context): HistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(context.applicationContext,
                        HistoryDatabase::class.java,
                        "HISTORY_DATABASE")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }

                return instance


            }

        }


    }


}