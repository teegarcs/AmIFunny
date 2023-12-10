package com.teegarcs.amifunny.features.create_joke

sealed class CreateJokeSE {
    data object JokeSaved: CreateJokeSE()
}
