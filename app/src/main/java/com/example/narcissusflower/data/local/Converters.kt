package com.example.narcissusflower.data.local

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long {
        return calendar.timeInMillis
    }

    @TypeConverter
    fun datestampToCalendar(time: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = time }
    }
}