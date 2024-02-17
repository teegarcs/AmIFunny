package com.teegarcs.amifunny.features.save_joke

import com.teegarcs.amifunny.core.mvi.KMPBaseViewModel
import com.teegarcs.amifunny.data.SavedJoke
import com.teegarcs.amifunny.data.getDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class KMPSavedJokeViewModel(
    _scope: CoroutineScope? = null
) : KMPBaseViewModel<SaveJokeState, SaveJokeSE, SaveJokeIntent>(_scope) {

    init {
        scope.launch {
            getDAO().retrieve<SavedJoke>().asFlow().collectLatest {
                val results = it.list.toList()
                updateState {
                    copy(jokes = results, isLoading = false)
                }
            }
        }
    }

    override fun buildInitialState() = SaveJokeState()

    override fun processIntent(intent: SaveJokeIntent) {
        when (intent) {
            is SaveJokeIntent.DeleteJoke -> deleteJoke(joke = intent.joke)
        }
    }

    private fun deleteJoke(joke: SavedJoke) {
        scope.launch {
            getDAO().delete(joke)
        }
    }
}