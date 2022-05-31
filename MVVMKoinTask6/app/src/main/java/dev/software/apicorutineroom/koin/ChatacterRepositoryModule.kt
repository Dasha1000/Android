package dev.software.apicorutineroom.koin

import dev.software.apicorutineroom.CharactersRepository
import org.koin.dsl.module

val charactersRepositoryModule = module {
    single{CharactersRepository(get()) }
}