package com.teegarcs.amifunny.network.joke

import com.teegarcs.amifunny.KMLogger
import com.teegarcs.amifunny.network.Networking
import com.teegarcs.amifunny.network.Networking.urlBuilder
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
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
    override suspend fun generateJoke(request: GenerateJokeRequest): JokeResponse {
        return Networking.performPost(urlBuilder("joke")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}