package com.aifunny.llm

import com.aifunny.beans.Networking
import com.aifunny.model.llm.LLMResponse
import com.aifunny.model.llm.toLLMRequest
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var networking: Networking

    @Value("\${PALM_API_KEY}")
    private lateinit var apiKey: String

    override fun sendPrompt(prompt: String, temperature: Float): String? {
        val response = networking.genAIWebClient
                .post()
                .uri {
                    it.path("/text-bison-001:generateText")
                    it.queryParam("key", apiKey)
                    it.build()
                }
                .bodyValue(prompt.toLLMRequest(temperature))
                .retrieve()
                .bodyToMono(LLMResponse::class.java)

        return response.map {
            it.candidates.getOrNull(0)?.output ?: "Oops something went wrong."
        }.block()
    }
}