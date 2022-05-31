package dev.software.apicorutineroom.database

import androidx.room.*
import dev.software.apicorutineroom.model.Characters
import dev.software.apicorutineroom.model.Persons

@Dao
interface DaoDB {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Characters>?)

    @Query("SELECT * FROM characters LIMIT :limit OFFSET :offset")
    suspend fun getCharacters(limit: Int, offset: Int ): List<Characters>

    @Query("DELETE FROM characters")
    suspend fun delete()

    @Transaction
    @Query("SELECT * FROM persons LIMIT :limit OFFSET :offset")
    suspend fun getTVPersons(limit: Int, offset: Int ): List<Persons>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVPersons(person: List<Persons>)

}