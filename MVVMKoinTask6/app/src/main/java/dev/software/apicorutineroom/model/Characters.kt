package dev.software.apicorutineroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Characters(
    @PrimaryKey
    @ColumnInfo(name = "char_id")
    val char_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "img")
    val img: String
)

