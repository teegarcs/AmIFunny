package com.teegarcs.amifunny.features.create_joke

import com.teegarcs.amifunny.core.mvi.KMPBaseViewModel
import com.teegarcs.amifunny.data.SavedJoke
import com.teegarcs.amifunny.data.getDAO
import com.teegarcs.amifunny.network.joke.GenerateJokeRequest
import com.teegarcs.amifunny.network.joke.JokeType
import com.teegarcs.amifunny.network.joke.retrieveJokeService
import com.teegarcs.amifunny.network.model_builder.ModelState
import com.teegarcs.amifunny.network.model_builder.executeAPIModelRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class KMPCreateJokeViewModel(
    _scope: CoroutineScope? = null
) : KMPBaseViewModel<CreateJokeState, CreateJokeSE, CreateJokeIntent>(_scope) {

    private var currentJokeNouns: List<String> = emptyList()
    private val jokeService = retrieveJokeService()

    override fun buildInitialState() = CreateJokeState()

    override fun processIntent(intent: CreateJokeIntent) {
        when (intent) {
            is CreateJokeIntent.RequestCreateJoke -> generateJoke(prompts = intent)
            CreateJokeIntent.SaveCurrentJoke -> saveJoke()
        }
    }

    private fun generateJoke(prompts: CreateJokeIntent.RequestCreateJoke) {
        updateState { copy(isLoading = true) }
        scope.launch {
            val splitReg = Regex("[\\s,]")
            val type = JokeType.valueOf(prompts.jokeType)
            currentJokeNouns = prompts.jokePrompt.split(splitReg)
            val joke = executeAPIModelRequest {
                jokeService.generateJoke(
                    GenerateJokeRequest(
                        jokeType = type,
                        jokeNouns = currentJokeNouns,
                        familyFriendly = true
                    )
                )
            }

            val message = when (joke) {
                is ModelState.Success -> {
                    joke.response.joke
                }

                is ModelState.Error -> {
                    joke.message
                }

                else -> {
                    "Something went wrong, please try again."
                }
            }
            updateState {
                copy(
                    isLoading = false,
                    generatedJoke = message,
                    jokeSaved = false
                )
            }
        }
    }

    private fun saveJoke() {
        scope.launch {
            getDAO().insert(
                SavedJoke(
                    joke = currentState.generatedJoke.orEmpty(),
                    nouns = currentJokeNouns.joinToString()
                )
            )

            sendSideEffect(CreateJokeSE.JokeSaved)

            updateState {
                copy(
                    jokeSaved = true
                )
            }
        }
    }
}