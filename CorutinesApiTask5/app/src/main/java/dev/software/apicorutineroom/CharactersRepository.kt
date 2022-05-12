package dev.software.apicorutineroom


import dev.software.apicorutineroom.retrofit.BreakingBadApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CharactersRepository(private val charactersApi: BreakingBadApi) {

    suspend fun getCharacters(limit: Int, offset: Int) = withContext(Dispatchers.IO) {
        delay(10000)
        charactersApi.getCharacters(limit,offset)
    }
}