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
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

    /**
     * Отформатировать время в миллисекундах в формат день.месяц.год часы:минуты
     */
    fun formatMillisToReadableDate(millis: Long): String {
        val date = Date(millis)
        return dateFormat.format(date)
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

}