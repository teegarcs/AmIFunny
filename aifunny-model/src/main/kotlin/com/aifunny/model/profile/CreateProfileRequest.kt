package com.aifunny.model.profile

import kotlinx.serialization.Serializable

@Serializable
data class CreateProfileRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val readingLevel: Int //1-10
)
