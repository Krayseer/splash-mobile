package ru.anykeyers.partner_app.domain.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.ui.activity.MainActivity

/**
 * Обработчик уведомлений
 */
class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        sendNotification(
            context = context,
            title = "Уважаемый партнер, вам пришло напоминание!",
            message = intent.getStringExtra(NotificationConstant.NOTIFICATION_KEY).orEmpty()
        )
    }

    private fun sendNotification(context: Context, title: String, message: String) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) ?: return

        val notification = NotificationCompat.Builder(
            context,
            context.getString(R.string.profile_notification_self)
        )
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(createPendingIntent(context))
            .build()

        notificationManager.notify(0, notification)
    }

    private fun createPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

}