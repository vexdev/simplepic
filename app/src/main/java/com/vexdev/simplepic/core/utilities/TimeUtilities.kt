package com.vexdev.simplepic.core.utilities

import java.text.SimpleDateFormat
import java.util.*


/**
 * Converts a millisecond based timestamp into a string with the format:
 * yyyy-MM-dd HH:mm
 */
fun timestampToString(timestampMilliseconds: Long, tz: TimeZone = TimeZone.getDefault()): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ROOT)
    val currenDate = Date(timestampMilliseconds)
    sdf.timeZone = tz
    return sdf.format(currenDate)
}
