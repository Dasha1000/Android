package dev.software.apicorutineroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.software.apicorutineroom.model.Characters

@Database(entities = [Characters::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    companion object {

        private var database: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return database ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "characters_database"
            )
                //.allowMainThreadQueries()
                .build()
                .also { database = it }
        }
    }
}