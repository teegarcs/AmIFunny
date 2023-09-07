package com.aifunny.model.joke

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val joke: String
)
