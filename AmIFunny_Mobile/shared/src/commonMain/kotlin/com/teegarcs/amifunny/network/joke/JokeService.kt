package com.teegarcs.amifunny.network.joke

import com.teegarcs.amifunny.network.Networking
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private var jokeService: JokeService? = null

/**
 * Statically retrieve singleton joke Service.
 * To be replaced once DI is in place.
 */
fun retrieveJokeService(): JokeService {
    if (jokeService == null)
        jokeService = JokeServiceImpl()

    return jokeService!!
}

interface JokeService {
    suspend fun generateJoke(request: GenerateJokeRequest): JokeResponse
}

class JokeServiceImpl : JokeService {
    private val networking = Networking()
    override suspend fun generateJoke(request: GenerateJokeRequest): JokeResponse {
        return networking.performPost(networking.urlBuilder("joke")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}