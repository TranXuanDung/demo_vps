package com.dungtx.mvvm.ui.utils

import java.text.SimpleDateFormat
import java.util.*

class FormatUtils() {

    init {
        throw AssertionError()
    }

    fun getDurationString(seconds: Int): String? {
        val date = Date(seconds * 1000L)
        val formatter = SimpleDateFormat(if (seconds >= 3600) "HH:mm:ss" else "mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        return formatter.format(date)
    }
}