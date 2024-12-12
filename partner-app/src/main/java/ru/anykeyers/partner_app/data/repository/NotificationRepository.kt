package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.NotificationAPI
import ru.anykeyers.partner_app.domain.entity.Notification
import ru.anykeyers.partner_app.domain.repository.INotificationRepository

class NotificationRepository(
    private val notificationAPI: NotificationAPI
) : INotificationRepository {

    override suspend fun loadNotifications(): List<Notification> {
        return withContext(Dispatchers.IO) {
            notificationAPI.loadNotifications()
        }
    }

    override suspend fun deleteNotification(notification: Notification) {
        return withContext(Dispatchers.IO) {
            notificationAPI.deleteNotification(notification.id)
        }
    }

}