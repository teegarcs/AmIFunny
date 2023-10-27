package com.teegarcs.amifunny.features.create_joke

import com.teegarcs.amifunny.network.joke.JokeType

data class CreateJokeState(
    val isLoading: Boolean = false,
    val generatedJoke: String? = null,
    val jokeSaved: Boolean = false,
    val jokeTypes: List<JokeType> = JokeType.entries.toList()
)
