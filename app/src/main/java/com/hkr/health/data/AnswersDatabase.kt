package com.hkr.health.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Answer::class], version = 1)
abstract class AnswersDatabase: RoomDatabase() {
    abstract fun answersDao(): AnswersDao

    companion object {

        private var INSTANCE: AnswersDatabase? = null

        fun getInstance(context: Context): AnswersDatabase {
            // Only allow one thread access to the object at a time
            synchronized(this) {

                var instance = INSTANCE

                // Create a new instance if none have been created
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnswersDatabase::class.java,
                        "answers_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}