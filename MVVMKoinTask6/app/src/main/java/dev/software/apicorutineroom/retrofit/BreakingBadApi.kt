package dev.software.apicorutineroom.retrofit

import dev.software.apicorutineroom.model.CharacterDescription
import dev.software.apicorutineroom.model.Characters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreakingBadApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<Characters>

    @GET("characters/{char_id}")
    suspend fun characterDescription(
        @Path("char_id") char_id: Int
    ): List<CharacterDescription>
}

//https://www.breakingbadapi.com/api/characters/?limit=10&offset=5//example