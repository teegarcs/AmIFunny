package com.aifunny.model.llm

import kotlinx.serialization.Serializable

@Serializable
data class LLMResponse(val candidates: List<LLMCandidate>)
@Serializable
data class LLMCandidate(val output: String)

