package dev.software.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "genre")
    var genre: String?,
    @ColumnInfo(name = "author")
    var author: String?
)