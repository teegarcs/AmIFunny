package com.aifunny.model.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val readingLevel: Int
)
