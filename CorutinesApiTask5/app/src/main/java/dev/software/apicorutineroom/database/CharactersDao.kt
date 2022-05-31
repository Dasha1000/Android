package dev.software.apicorutineroom.database

import androidx.room.*
import dev.software.apicorutineroom.model.Characters

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Characters>)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Characters>

}