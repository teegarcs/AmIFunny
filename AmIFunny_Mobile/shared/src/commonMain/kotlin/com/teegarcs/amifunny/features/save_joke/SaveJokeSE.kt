package com.teegarcs.amifunny.features.save_joke

sealed class SaveJokeSE {
    data object JokeDeleted : SaveJokeSE()
}
