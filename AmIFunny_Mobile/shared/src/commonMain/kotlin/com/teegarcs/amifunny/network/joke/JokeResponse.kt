package com.teegarcs.amifunny.network.joke

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val joke: String
)
