package com.aifunny.model.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusCode: Int?,
    val message: String
)
