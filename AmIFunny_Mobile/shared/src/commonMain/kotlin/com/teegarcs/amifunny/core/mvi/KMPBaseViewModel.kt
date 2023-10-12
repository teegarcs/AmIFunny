package com.teegarcs.amifunny.core.mvi

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * BaseViewModel for MVI that sets up the structure for State, SideEffects, and Intents.
 */
abstract class KMPBaseViewModel<VS : Any, SE : Any, I : Any>(
    _scope: CoroutineScope?
) {
    @NativeCoroutineScope
    val scope = _scope ?: MainScope()

    private val _viewState: MutableStateFlow<VS> by lazy {
        MutableStateFlow(buildInitialState())
    }

    @NativeCoroutinesState
    val viewState: StateFlow<VS>
        get() = _viewState.asStateFlow()

    val currentState: VS
        get() = viewState.value

    @Suppress("MemberVisibilityCanBePrivate")
    private val _sideEffectsChannel = Channel<SE>(Channel.UNLIMITED)

    @NativeCoroutines
    val sideEffects: Flow<SE>
        get() = _sideEffectsChannel.receiveAsFlow()

    /**
     * Abstract function required to be implemented to build the Initial UI State Object. This
     * State will automatically be supplied to the viewState.
     *
     * @return ViewState Object
     */
    abstract fun buildInitialState(): VS


    /**
     * Abstract function to be implemented to handle intents received from the UI.
     *
     * @param intent - Intent to be handled and reduced.
     */
    abstract fun processIntent(intent: I)

    /**
     * Function to post a SideEffect to the SideEffect Channel.
     *
     * @param sideEffect - the SideEffect to post to the channel.
     */
    protected fun sendSideEffect(sideEffect: SE) {
        _sideEffectsChannel.trySend(sideEffect)
    }

    /**
     * Update the MutableStateFlow with the provided State Object.
     *
     * @param updated - state to push to the MutableStateFlow
     * @return true if the StateFlow was updated successfully.
     */
    protected fun updateState(updated: VS): Boolean {
        _viewState.value = updated
        return if (_viewState.value != updated) {
            _viewState.value = updated
            true
        } else {
            false
        }
    }

    protected fun updateState(updateFunction: VS.() -> VS): Boolean =
        updateState(updateFunction(_viewState.value))

    /**
     * Call to clear the VM from running
     */
    fun clear(){
        scope.cancel()
    }
}