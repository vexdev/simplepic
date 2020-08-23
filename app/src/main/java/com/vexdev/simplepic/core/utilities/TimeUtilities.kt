package com.vexdev.simplepic.core.utilities

import java.text.SimpleDateFormat
import java.util.*


/**
 * Converts a millisecond based timestamp into a string with the format:
 * yyyy-MM-dd HH:mm
 */
fun timestampToString(timestampMilliseconds: Long): String {
    val calendar = Calendar.getInstance()
    val tz = TimeZone.getDefault()
    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val currenTimeZone = Date(timestampMilliseconds)
    return sdf.format(currenTimeZone)
}
