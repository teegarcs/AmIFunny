package com.teegarcs.amifunny.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class Networking {
    private val ROOT_URL = "http://192.168.86.25:8080/"

    internal val urlBuilder: (end: String) -> String = {
        ROOT_URL + it
    }
    private val ktorClient: HttpClient by lazy {
        HttpClient {
            this.expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }
        }
    }

    /**
     * Wrapper around ktor Post that switches coroutine context so we don't have to
     * remember to do this in each call.
     */
    suspend fun performPost(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit
    ): HttpResponse = withContext(Dispatchers.IO) {
        ktorClient.post(urlString, block)
    }
}
