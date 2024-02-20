package com.teegarcs.amifunny.android.saved

import androidx.lifecycle.viewModelScope
import com.teegarcs.amifunny.android.core.BaseAndroidViewModel
import com.teegarcs.amifunny.features.save_joke.KMPSavedJokeViewModel
import com.teegarcs.amifunny.features.save_joke.SaveJokeIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveJokeViewModel @Inject constructor() : BaseAndroidViewModel<KMPSavedJokeViewModel>() {
    override val coreViewModel: KMPSavedJokeViewModel =
        KMPSavedJokeViewModel(viewModelScope, SampleNativeAbstractionImpl())
    val state = coreViewModel.viewState

    override fun processIntent(intent: Any) {
        coreViewModel.processIntent(intent as SaveJokeIntent)
    }
}
