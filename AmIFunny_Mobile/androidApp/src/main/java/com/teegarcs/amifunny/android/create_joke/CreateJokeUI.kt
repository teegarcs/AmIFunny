package com.teegarcs.amifunny.android.create_joke

import androidx.compose.runtime.Composable
import com.teegarcs.amifunny.android.core.BaseComposeUI
import com.teegarcs.amifunny.features.create_joke.CreateJokeIntent
import com.teegarcs.amifunny.features.create_joke.CreateJokeState

class CreateJokeUI : BaseComposeUI<CreateJokeState, CreateJokeIntent>() {

    @Composable
    override fun BuildUI(state: CreateJokeState, processIntent: (CreateJokeIntent) -> Unit) {

    }

}
