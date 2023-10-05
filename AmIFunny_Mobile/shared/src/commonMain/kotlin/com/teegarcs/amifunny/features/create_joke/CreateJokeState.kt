package com.teegarcs.amifunny.features.create_joke

data class CreateJokeState(
    val isLoading: Boolean = false,
    val generatedJoke: String? = null
)
