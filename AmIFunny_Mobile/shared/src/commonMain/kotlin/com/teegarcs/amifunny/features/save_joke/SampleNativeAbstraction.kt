package com.teegarcs.amifunny.features.save_joke

/**
 * Sample of how to use the abstraction of some native framework without using expects/actuals
 */
interface SampleNativeAbstraction {

    fun loadSomethingNative(completion: (String) -> Unit)
}