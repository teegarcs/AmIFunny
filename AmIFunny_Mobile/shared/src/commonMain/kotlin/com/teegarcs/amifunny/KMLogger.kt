package com.teegarcs.amifunny

expect object KMLogger {
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun e(tag: String, message: String)
    fun v(tag: String, message: String)
}
