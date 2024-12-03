package ru.anykeyers.partner_app.domain.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.anykeyers.partner_app.R

/**
 * Конфигуратор каналов уведомлений
 */
class NotificationConfigurator(
    private val notificationManager: NotificationManagerCompat,
    private val context: Context
) {

    /**
     * Сконфигурировать каналы
     */
    fun configureChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        createChannel()
    }

    private fun createChannel() {
        val notificationChannel = NotificationChannel(
            context.resources.getString(R.string.notification_channel_main_id),
            context.resources.getString(R.string.notification_channel_main_name),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            enableLights(true)
            enableVibration(true)
            setShowBadge(true)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
        }
        notificationManager.createNotificationChannel(notificationChannel)
    }
}