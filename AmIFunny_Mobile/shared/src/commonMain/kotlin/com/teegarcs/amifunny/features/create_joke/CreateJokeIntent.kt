package com.teegarcs.amifunny.features.create_joke

import com.teegarcs.amifunny.network.joke.JokeType

sealed class CreateJokeIntent {
    data class RequestCreateJoke(
        val jokeType: JokeType,
        val jokePrompts: List<String>
    ) : CreateJokeIntent()
}
