package com.teegarcs.amifunny.android.create_joke

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teegarcs.amifunny.android.R
import com.teegarcs.amifunny.android.components.FunnyDropDown
import com.teegarcs.amifunny.android.components.LoaderWithMessage
import com.teegarcs.amifunny.android.core.BaseComposeUI
import com.teegarcs.amifunny.android.theme.FunnyTheme
import com.teegarcs.amifunny.features.create_joke.CreateJokeIntent
import com.teegarcs.amifunny.features.create_joke.CreateJokeState

class CreateJokeUI : BaseComposeUI<CreateJokeState, CreateJokeIntent>() {

    @Composable
    override fun BuildUI(state: CreateJokeState, processIntent: (CreateJokeIntent) -> Unit) {

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(scrollState)
        ) {

            Text(text = "Type nouns that you would like to appear in your joke.")

            val (typedNouns, setTypedNouns) = rememberSaveable { mutableStateOf("") }
            var jokeType by rememberSaveable { mutableStateOf<String?>(null) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = typedNouns,
                onValueChange = setTypedNouns
            )

            FunnyDropDown(
                R.string.joke_type_label,
                state.jokeTypes.map { it.name },
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 32.dp)
                    .fillMaxWidth()
            ) { typeIndex, typeValue ->
                jokeType = typeValue
            }

            if (state.isLoading) {
                LoaderWithMessage(
                    modifier = Modifier.fillMaxWidth(),
                    message = "Generating Joke"
                )
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = jokeType != null && typedNouns.isNotEmpty(),
                    onClick = {
                        processIntent(
                            CreateJokeIntent.RequestCreateJoke(
                                jokeType = jokeType.orEmpty(),
                                jokePrompt = typedNouns
                            )
                        )
                    }
                ) {
                    Text(text = "Generate Joke")
                }
            }

            if (state.generatedJoke != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    content = {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                text = state.generatedJoke.orEmpty()
                            )

                            Button(
                                modifier = Modifier.align(Alignment.End),
                                enabled = !state.jokeSaved,
                                onClick = {
                                    processIntent(CreateJokeIntent.SaveCurrentJoke)
                                }
                            ) {
                                val label = if (state.jokeSaved) {
                                    "Saved"
                                } else {
                                    "Save"
                                }
                                Text(text = label)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitialLoadPreview() {
    FunnyTheme {
        CreateJokeUI().BuildUI(state = CreateJokeState(), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    FunnyTheme {
        CreateJokeUI().BuildUI(state = CreateJokeState(isLoading = true), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun JokePreview() {
    FunnyTheme {
        CreateJokeUI().BuildUI(
            state = CreateJokeState(
                isLoading = false,
                generatedJoke = "Some Joke that is really funny."
            ), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun JokePreviewSaved() {
    FunnyTheme {
        CreateJokeUI().BuildUI(
            state = CreateJokeState(
                isLoading = false,
                generatedJoke = "Some Joke that is really funny.",
                jokeSaved = true
            ), processIntent = {})
    }
}