package dev.software.apicorutineroom.model

import com.google.gson.annotations.SerializedName

data class CharacterDescription(
    val name: String,
    @SerializedName("img")
    val url: String,
    val birthday: String,
    val nickname: String
)