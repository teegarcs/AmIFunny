package com.teegarcs.amifunny

import platform.Foundation.NSLog


actual object KMLogger {
    actual fun d(tag: String, message: String) {
        NSLog("$tag, $message")
    }

    actual fun i(tag: String, message: String) {
        NSLog("$tag, $message")
    }

    actual fun e(tag: String, message: String) {
        NSLog("$tag, $message")
    }

    actual fun v(tag: String, message: String) {
        NSLog("$tag, $message")
    }
}