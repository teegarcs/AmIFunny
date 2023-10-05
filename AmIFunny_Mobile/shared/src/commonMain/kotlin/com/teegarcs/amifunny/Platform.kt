package com.teegarcs.amifunny

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform