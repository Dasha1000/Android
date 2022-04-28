package dev.software.db.database

import androidx.room.*
import dev.software.db.model.Song

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(vararg item: Song) : LongArray

    @Delete
    fun deleteSong(vararg item: Song)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): List<Song>

    @Query("SELECT * FROM songs WHERE " +
            "title LIKE '%' || :search || '%' OR " +
            "genre LIKE '%' || :search || '%' OR "
            + "author LIKE '%' || :search || '%'")
    fun findAll(search: String?): List<Song>

    @Update
    fun updateSong(item: Song)
}