package com.aifunny.model.joke

import kotlinx.serialization.Serializable

@Serializable
data class GenerateJokeRequest(
    val jokeType: JokeType,
    val familyFriendly: Boolean,
    val jokeNouns: List<String>
)
