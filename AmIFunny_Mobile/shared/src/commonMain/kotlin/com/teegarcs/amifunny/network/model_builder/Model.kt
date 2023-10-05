package com.teegarcs.amifunny.network.model_builder

import com.teegarcs.amifunny.network.model_builder.BaseModel.Companion.NO_EXPIRY
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Model Wrapper to orchestrate calls to the new data data provider (typically api callls)
 * and to the cache/disk.
 */
class Model<T>(
    private val modelProvider: suspend () -> T,
    private val cachedModelProvider: (suspend () -> T?)? = null,
    private val modelSaver: (suspend (T) -> Unit)? = null
) {

    constructor(
        modelProvider: suspend () -> T,
    ) : this(modelProvider, null, null)

    /**
     * build the Model and output according to provided data providers.
     */
    fun build(maxAge: Long = NO_EXPIRY): Flow<ModelState<T>> = flow {
        emit(ModelState.Loading())

        val cachedModel = cachedModelProvider?.invoke()
        cachedModel?.let {
            val isExpired = if (it is List<*>) {
                if (it.isEmpty()) {
                    true//expired, won't emit data
                } else {
                    val listExpired = it.filterIsInstance<BaseModel>()
                        .find { baseModel -> baseModel.isExpired(maxAge) } != null
                    //emit data, but may be expired

                    emit(ModelState.Success(it, true, listExpired))

                    listExpired
                }
            } else {
                val modelExpired = (it as? BaseModel)?.let { castedModel ->
                    castedModel.isExpired(maxAge)
                } ?: false

                emit(ModelState.Success(it, true, modelExpired))

                modelExpired
            }

            if (!isExpired) {
                return@flow
            }
        }

        //if here, the cached data was either null or expired.
        val result = executeAPIModelRequest(networkRequest = modelProvider)

        if (result is ModelState.Success) {
            modelSaver?.invoke(result.response)
        }

        emit(result)
    }
}


/**
 * Utlity that wraps a ktor/network request and returns the result mapped in a model state.
 * Will always be either Success or Error, no loading is returned.
 */
suspend fun <T> executeAPIModelRequest(
    existingModel: T? = null,
    networkRequest: suspend () -> T,
): ModelState<T> {
    return try {
        val newData = networkRequest()
        ModelState.Success(newData)
    } catch (e: ResponseException) {
        ModelState.Error(
            message = e.response.bodyAsText(),
            code = e.response.status.value,
            response = existingModel
        )
    } catch (ge: Exception) {
        ModelState.Error(message = ge.message.orEmpty(), response = existingModel)
    }
}