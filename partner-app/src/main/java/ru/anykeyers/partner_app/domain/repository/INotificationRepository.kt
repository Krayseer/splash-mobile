package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Notification

/**
 * Сервис обработки уведомлений
 */
interface INotificationRepository {

    /**
     * Загрузить список уведомлений
     */
    suspend fun loadNotifications(): List<Notification>

    /**
     * Удалить уведомление
     */
    suspend fun deleteNotification(notification: Notification)

}