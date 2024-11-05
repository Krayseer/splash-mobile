package ru.anykeyers.partner_app.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Утилитарный класс для работы с датами
 */
object DateUtils {

    @SuppressLint("SimpleDateFormat")
    private val fullFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    @SuppressLint("SimpleDateFormat")
    private val dateTimeFormat = SimpleDateFormat("HH:mm")
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy")

    /**
     * Отформатировать время в миллисекундах в формат день.месяц.год часы:минуты
     */
    fun formatMillisToReadableDate(millis: Long): String {
        val date = Date(millis)
        return fullFormat.format(date)
    }

    /**
     * Отформатировать время в миллисекундах в человеко-читаемый формат
     */
    fun formatMillisToReadableTime(millis: Long): String {
        val duration = millis.toDuration(DurationUnit.MILLISECONDS)
        val hours = duration.inWholeHours
        val minutes = duration.inWholeMinutes % 60
        val seconds = duration.inWholeSeconds % 60

        return when {
            hours > 0 -> "$hours ч ${if (minutes > 0) "$minutes мин" else ""} ${if (seconds > 0) "$seconds сек" else ""}".trim()
            minutes > 0 -> "$minutes мин ${if (seconds > 0) "$seconds сек" else ""}".trim()
            else -> "$seconds сек"
        }
    }

    /**
     * Отформатировать диапазон времени (начало - конец) в читаемый формат
     * Формат: "часы:минуты-часы:минуты * день.месяц.год"
     */
    fun formatRangeToReadableTime(start: Long, end: Long): String {
        val startTime = dateTimeFormat.format(Date(start))
        val endTime = dateTimeFormat.format(Date(end))
        val date = dateFormat.format(Date(start))

        return "$startTime ~ $endTime : $date"
    }

}