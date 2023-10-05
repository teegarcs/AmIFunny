package com.teegarcs.amifunny

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

object DateUtils {

    /**
     * Utility to get the current date in Kotlinx.datetime.LocaleDate
     */
    fun getToday(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }

    /**
     * Calculates the number of days between now and the new year in the current timezone
     */
    fun daysUntilNewYear(): Int {
        val today = getToday()
        val closestNewYear = LocalDate(today.year + 1, 1, 1)
        return today.daysUntil(closestNewYear)
    }
}