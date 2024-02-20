package com.teegarcs.amifunny.android.saved

import com.teegarcs.amifunny.features.save_joke.SampleNativeAbstraction

/**
 * Sample of how to use the abstraction of some native framework without using expects/actuals
 */
class SampleNativeAbstractionImpl : SampleNativeAbstraction {
    override fun loadSomethingNative(completion: (String) -> Unit) {
        //DO SOMETHING NATIVE!
        completion("result")
    }
}