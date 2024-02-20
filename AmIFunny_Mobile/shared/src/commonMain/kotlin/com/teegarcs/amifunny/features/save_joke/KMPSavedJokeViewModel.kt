package com.teegarcs.amifunny.features.save_joke

import co.touchlab.kermit.Logger
import com.teegarcs.amifunny.core.mvi.KMPBaseViewModel
import com.teegarcs.amifunny.data.SavedJoke
import com.teegarcs.amifunny.data.getDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class KMPSavedJokeViewModel(
    _scope: CoroutineScope? = null,
    private val abstraction: SampleNativeAbstraction
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

    /**
     * Sample of how to use the abstraction of some native framework without using expects/actuals
     */
    private fun exampleAbstractionUse() {
        abstraction.loadSomethingNative {
            Logger.i { "RESULT: $it" }
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