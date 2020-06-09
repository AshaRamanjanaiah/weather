package com.weather.au.Utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*

class Utils {
    companion object {
        const val SORT_BY_A_TO_Z = "aToz"
        const val SORT_BY_TEMPERATURE = "temperature"
        const val SORT_BY_LAST_UPDATED = "last_updated"

        fun getDateTime(s: Long): String? {

            val sdf = SimpleDateFormat("hh:mma dd MMMM YYYY ")
            val netDate = Date(s)
            val date =sdf.format(netDate)

            return date.toString()
        }
    }
}
