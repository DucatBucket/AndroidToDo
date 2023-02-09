package com.example.todolist

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): LocalDate? {
        return value?.let { Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    @TypeConverter
    fun dateToTimestamp(localDate:LocalDate?): Long? {
        return localDate?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
    }

    @TypeConverter
    fun timeFromSecondOfDay(seconds: Int?): LocalTime?{
        return seconds?.toLong()?.let { LocalTime.ofSecondOfDay(it) }
    }

    @TypeConverter
    fun timeToSecondOfDay(localTime: LocalTime?): Int? {
        return localTime?.toSecondOfDay()
    }
}