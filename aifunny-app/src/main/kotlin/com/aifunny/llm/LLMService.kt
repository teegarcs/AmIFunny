package com.aifunny.llm

import com.google.ai.generativelanguage.v1beta2.GenerateTextRequest
import com.google.ai.generativelanguage.v1beta2.TextPrompt
import com.google.ai.generativelanguage.v1beta2.TextServiceClient
import com.google.ai.generativelanguage.v1beta2.TextServiceSettings
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.api.gax.rpc.FixedHeaderProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * Generic Service to support sending a prompt and receiving a text response.
 */
interface LLMService {
    /**
     * Function to send a String prompt and get a prompt response back.
     * @param prompt - the prompt for the model
     * @param temperature - lower 0-1. Lower the number the less random/creative.
     */
    fun sendPrompt(prompt: String, temperature: Float = 0.5f): String?
}

@Service
class LLMServiceImpl() : LLMService {

    @Value("\${PALM_API_KEY}")
    private lateinit var apiKey: String

    private val textClient: TextServiceClient by lazy {
        val headers = mapOf("x-goog-api-key" to apiKey)
        val provider = InstantiatingGrpcChannelProvider.newBuilder()
            .setHeaderProvider(FixedHeaderProvider.create(headers))
            .build()

        val settings = TextServiceSettings.newBuilder()
            .setTransportChannelProvider(provider)
            .setCredentialsProvider(FixedCredentialsProvider.create(null))
            .build()

        TextServiceClient.create(settings)
    }

    override fun sendPrompt(prompt: String, temperature: Float): String? {
        val promptItem = TextPrompt.newBuilder()
            .setText(prompt)
            .build()

        val request = GenerateTextRequest.newBuilder()
            .setModel("models/text-bison-001") // Required, which model to use to generate the result
            .setPrompt(promptItem) // Required
            .setTemperature(temperature)
            .setCandidateCount(1) // Optional, the number of generated texts to return
            .build()

        val response = textClient.generateText(request)
        System.out.println("LLM Response: ${response.toString()}")

        return response.candidatesList.getOrNull(0)?.output
    }
}