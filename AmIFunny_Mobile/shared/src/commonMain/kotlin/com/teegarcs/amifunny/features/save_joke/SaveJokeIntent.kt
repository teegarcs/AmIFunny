package com.teegarcs.amifunny.features.save_joke

import com.teegarcs.amifunny.data.SavedJoke

sealed class SaveJokeIntent {
    data class DeleteJoke(val joke: SavedJoke) : SaveJokeIntent()
}