package com.teegarcs.amifunny.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teegarcs.amifunny.android.components.Loader
import com.teegarcs.amifunny.android.create_joke.CreateJokeViewModel
import com.teegarcs.amifunny.features.create_joke.CreateJokeIntent
import com.teegarcs.amifunny.features.create_joke.CreateJokeState
import com.teegarcs.amifunny.network.joke.JokeType

class MainActivity : ComponentActivity() {

    private val viewModel: CreateJokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewState = viewModel.state.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateJokeView(
                        state = viewState.value,
                        processIntent = viewModel::processIntent
                    )
                }
            }
        }
    }
}

@Composable
fun CreateJokeView(state: CreateJokeState, processIntent: (CreateJokeIntent) -> Unit) {
    if (state.isLoading) {
        Loader()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.generatedJoke ?: "Tap below to generate joke!"
            )

            Button(
                onClick = {
                    processIntent(
                        CreateJokeIntent.RequestCreateJoke(
                            jokeType = JokeType.Dad,
                            jokePrompts = listOf("Dog", "Cat", "Giraffes")
                        )
                    )
                }
            ) {
                Text(text = "Generate Joke")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    MyApplicationTheme {
        CreateJokeView(state = CreateJokeState(isLoading = true), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun JokePreview() {
    MyApplicationTheme {
        CreateJokeView(
            state = CreateJokeState(
                isLoading = false,
                generatedJoke = "Some Joke that is really funny."
            ), processIntent = {})
    }
}
