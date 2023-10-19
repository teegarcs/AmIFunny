package com.teegarcs.amifunny.android.core

import androidx.compose.runtime.Composable

/**
 * base composable view to enforce a consistent pattern and set up a path for other
 * extensible defaults to be added.
 */
abstract class BaseComposeUI<State, Intent> {

    @Composable
    abstract fun BuildUI(state: State, processIntent: (Intent) -> Unit)
}