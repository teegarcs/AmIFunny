package com.teegarcs.amifunny.android.create_joke

import androidx.lifecycle.viewModelScope
import com.teegarcs.amifunny.android.core.BaseAndroidViewModel
import com.teegarcs.amifunny.features.create_joke.CreateJokeIntent
import com.teegarcs.amifunny.features.create_joke.KMPCreateJokeViewModel

class CreateJokeViewModel : BaseAndroidViewModel<KMPCreateJokeViewModel>() {
    override val coreViewModel: KMPCreateJokeViewModel = KMPCreateJokeViewModel(viewModelScope)
    val state = coreViewModel.viewState
    override fun processIntent(intent: Any) {
        coreViewModel.processIntent(intent as CreateJokeIntent)
    }
}