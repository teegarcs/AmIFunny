package com.teegarcs.amifunny.android.core

import androidx.lifecycle.ViewModel
import com.teegarcs.amifunny.core.mvi.KMPBaseViewModel

abstract class BaseAndroidViewModel<T : KMPBaseViewModel<*, *, *>> : ViewModel() {
    abstract val coreViewModel: T
    abstract fun processIntent(intent: Any)
}