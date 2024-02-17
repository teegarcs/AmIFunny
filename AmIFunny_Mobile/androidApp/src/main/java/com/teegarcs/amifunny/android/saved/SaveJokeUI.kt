package com.teegarcs.amifunny.android.saved

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teegarcs.amifunny.android.components.LoaderWithMessage
import com.teegarcs.amifunny.android.core.BaseComposeUI
import com.teegarcs.amifunny.android.theme.FunnyTheme
import com.teegarcs.amifunny.data.SavedJoke
import com.teegarcs.amifunny.features.save_joke.SaveJokeIntent
import com.teegarcs.amifunny.features.save_joke.SaveJokeState

class SaveJokeUI : BaseComposeUI<SaveJokeState, SaveJokeIntent>() {
    @Composable
    override fun BuildUI(state: SaveJokeState, processIntent: (SaveJokeIntent) -> Unit) {

        if (state.isLoading) {
            LoadingState()
        } else if (state.jokes.isEmpty()) {
            EmptyState()
        } else {
            JokeList(jokes = state.jokes, processIntent = processIntent)
        }

    }

    @Composable
    fun LoadingState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoaderWithMessage(
                modifier = Modifier.fillMaxWidth(),
                message = "Loading Saved Jokes"
            )
        }
    }

    @Composable
    fun EmptyState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No Saved Jokes")
        }
    }

    @Composable
    fun JokeList(jokes: List<SavedJoke>, processIntent: (SaveJokeIntent) -> Unit) {
        val listState = rememberLazyListState()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(jokes) {
                JokeItem(joke = it, processIntent = processIntent)
            }
        }
    }

    @Composable
    private fun JokeItem(joke: SavedJoke, processIntent: (SaveJokeIntent) -> Unit) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        processIntent(SaveJokeIntent.DeleteJoke(joke))
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "delete",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = joke.joke)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    fontSize = 12.sp,
                    text = joke.nouns,
                    textAlign = TextAlign.End
                )
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedJokeLoading() {
    FunnyTheme {
        SaveJokeUI().BuildUI(
            state = SaveJokeState(isLoading = true), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SavedJokeEmpty() {
    FunnyTheme {
        SaveJokeUI().BuildUI(
            state = SaveJokeState(isLoading = false), processIntent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SavedJokeList() {
    FunnyTheme {
        val joke = SavedJoke(
            joke = "Some joke",
            nouns = "Noun1, Noun2"
        )
        SaveJokeUI().BuildUI(
            state = SaveJokeState(isLoading = false, jokes = List(5) { joke }), processIntent = {})
    }
}