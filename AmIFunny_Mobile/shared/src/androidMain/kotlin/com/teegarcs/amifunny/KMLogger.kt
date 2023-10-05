package com.teegarcs.amifunny

import android.util.Log

actual object KMLogger {
    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    actual fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    actual fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

    actual fun v(tag: String, message: String) {
        Log.v(tag, message)
    }
}