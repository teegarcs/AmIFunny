package com.teegarcs.amifunny.android.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.teegarcs.amifunny.core.mvi.KMPBaseViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

abstract class BaseAndroidViewModel<T : KMPBaseViewModel<*, *, *>> : ViewModel() {
    abstract val coreViewModel: T
    abstract fun processIntent(intent: Any)
}


/**
 * Observe side effects emitted by the side effect channel. The same instance of ReceivedChannel
 * is returned each time for the observation.
 *
 * This way of observing does not close the ReceiveChannel emitted when the scope is cancelled.
 * This is important to handle the lifecycle of the UI correctly and allow the channel to remain
 * open
 */
inline fun <reified SE : Any> BaseAndroidViewModel<*>.observeSideEffects(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.RESUMED,
    crossinline sideEffectFunction: (SE) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        coreViewModel.sideEffects.flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .filterIsInstance<SE>().collect {
                sideEffectFunction(it)
            }
    }
}
