package dev.software.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.software.db.model.Song

@Database(entities = [Song::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun songDao(): SongDao

    companion object {

        private var database: AppDatabase? = null
        private val appDatabase get() = requireNotNull(database)

        fun getDatabase(context: Context): AppDatabase {

            database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "songs_database.db"
            )
                    .allowMainThreadQueries()
                    .build()

            return appDatabase
        }
    }
}