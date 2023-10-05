package com.teegarcs.amifunny.network.model_builder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter


sealed class ModelState<T> {
    data class Success<T>(
        val response: T,
        val fromCache: Boolean = false,
        val isExpired: Boolean = false
    ) : ModelState<T>()

    data class Error<T>(
        val message: String,
        val code: Int? = null,
        val response: T? = null
    ) : ModelState<T>()

    class Loading<T> : ModelState<T>()

    /**
     * Util operation for quick access of response
     */
    fun response(): T? = when (this) {
        is Success<T> -> this.response
        is Error -> this.response
        is Loading -> null
    }
}


/**
 * Utility that will filter out loading states if we don't care about them.
 */
fun <T> Flow<ModelState<T>>.filterTerminal() = this.filter {
    it is ModelState.Success || it is ModelState.Error
}.distinctUntilChanged()

/**
 * Utility that will filter out any successful value that is marked as expired.
 */
fun <T> Flow<ModelState<T>>.filterNotExpired() = this.filter {
    (it as? ModelState.Success)?.run {
        !isExpired
    } ?: true
}.distinctUntilChanged()