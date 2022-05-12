package dev.software.apicorutineroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Characters(
    @PrimaryKey
    @SerializedName("char_id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    @SerializedName("img")
    val url: String
)