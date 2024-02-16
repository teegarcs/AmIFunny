package com.teegarcs.amifunny.features.save_joke

import com.teegarcs.amifunny.data.SavedJoke

data class SaveJokeState(
    val jokes: List<SavedJoke> = emptyList(),
    val isLoading: Boolean = true,
)
