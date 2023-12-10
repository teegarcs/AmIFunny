package com.aifunny.beans

import com.aifunny.json.JsonParser
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class Networking {

    private val BISON_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta3/models"

    val genAIWebClient: WebClient by lazy {
        WebClient.builder()
                .baseUrl(BISON_ENDPOINT)
                .defaultHeader(
                        HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE,
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
                ).codecs {
                    it.defaultCodecs()
                            .kotlinSerializationJsonDecoder(
                                    KotlinSerializationJsonDecoder(
                                            JsonParser().json
                                    )
                            )
                }
                .build()
    }
}