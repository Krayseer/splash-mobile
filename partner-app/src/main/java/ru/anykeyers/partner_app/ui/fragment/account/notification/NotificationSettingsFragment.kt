package ru.anykeyers.partner_app.ui.fragment.account.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentNotificationSettingsBinding
import ru.anykeyers.partner_app.domain.notification.NotificationConstant
import ru.anykeyers.partner_app.domain.notification.NotificationReceiver
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar

/**
 * Фрагмент настроек уведомлений пользователя
 */
class NotificationSettingsFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotificationSettingsBinding.inflate(inflater, container, false)

        binding.timePickerButton.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.timeEditText.setText(formattedTime)
            }, hour, minute, true).show()
        }

        binding.submitButton.setOnClickListener {
            val timeInput = binding.timeEditText.text.toString()
            if (!validateTimeInput(timeInput)) {
                binding.timeEditText.error = "Введите время в формате HH:mm"
            } else {
                parseTimeInput(timeInput)?.let {
                    scheduleNotification(it)
                    Log.d("Notification", "Уведомление установлено на $timeInput")
                }
            }
        }

        return binding.root
    }

    private fun validateTimeInput(input: String): Boolean {
        val timePattern = "^([01]\\d|2[0-3]):([0-5]\\d)$".toRegex()
        return timePattern.matches(input)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseTimeInput(input: String): LocalTime? {
        return try {
            val (hour, minute) = input.split(":").map { it.toInt() }
            LocalTime.of(hour, minute)
        } catch (e: Exception) {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleNotification(selectedTime: LocalTime) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val now = LocalDate.now()
        val dateTime = LocalDateTime.of(now, selectedTime)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NotificationConstant.NOTIFICATION_KEY, "Проверьте свой список заказов!")
        }

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
            Log.d("AlarmManager", "Уведомление успешно установлено на $timeInMillis")
        } catch (e: SecurityException) {
            Log.e("AlarmManager", "Не удалось установить напоминание", e)
        }
    }

}