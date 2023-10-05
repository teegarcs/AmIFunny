package com.teegarcs.amifunny.network.model_builder

import kotlinx.datetime.Clock

abstract class BaseModel {

    companion object {
        const val NO_EXPIRY = -1L
        const val ONE_MINUTE = 60L * 1000L
        const val FIVE_MINUTE = 60L * 1000L * 5
        const val ONE_HOUR = 60L * 60L * 1000L
        const val ONE_DAY = ONE_HOUR * 24L
        const val ONE_WEEK = ONE_DAY * 7L
    }

    var lastUpdateTime: Long = Clock.System.now().toEpochMilliseconds()

    /**
     * Utility to check if this model is older than the provided age.
     */
    fun isExpired(maxAge: Long) =
        maxAge != NO_EXPIRY && (Clock.System.now().toEpochMilliseconds() - lastUpdateTime) > maxAge
}
