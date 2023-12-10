package com.aifunny.model.llm

import kotlinx.serialization.Serializable

@Serializable
data class LLMRequest(val prompt: LLMPrompt, val temperature: Float)

@Serializable
data class LLMPrompt(
        val text: String
)

fun String.toLLMRequest(temperature: Float = 0.5F) = LLMRequest(LLMPrompt(text = this), temperature = temperature)
