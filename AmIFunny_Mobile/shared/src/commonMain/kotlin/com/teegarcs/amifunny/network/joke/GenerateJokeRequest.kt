package com.teegarcs.amifunny.network.joke

import kotlinx.serialization.Serializable

//TODO figure out how to share with models from rest api
@Serializable
data class GenerateJokeRequest(
    val jokeType: JokeType,
    val familyFriendly: Boolean,
    val jokeNouns: List<String>
)