package com.vexdev.simplepic.core.utilities

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeUtilitiesTest {
    private val utc = TimeZone.getTimeZone("UTC")

    @Test
    fun zeroTimestampIsConverted() {
        val actual = timestampToString(0, utc)
        assertEquals("1970-01-01 00:00", actual)
    }

    @Test
    fun timezoneIsTakenIntoConsideration() {
        val actual = timestampToString(0, TimeZone.getTimeZone("CET"))
        assertEquals("1970-01-01 01:00", actual)
    }

}
