package com.teegarcs.amifunny.features.create_joke

sealed class CreateJokeIntent {
    data class RequestCreateJoke(
        val jokeType: String,
        val jokePrompt: String
    ) : CreateJokeIntent()

    data object SaveCurrentJoke : CreateJokeIntent()
}
