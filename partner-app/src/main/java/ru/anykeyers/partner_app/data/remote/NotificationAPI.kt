package ru.anykeyers.partner_app.data.remote

import retrofit2.http.GET
import ru.anykeyers.partner_app.domain.entity.Notification

/**
 * API для работы с удаленным сервисом уведомлений
 */
interface NotificationAPI {

    /**
     * Загрузить список уведомлений
     */
    @GET("${WebConstant.NOTIFICATION_URL}/push")
    suspend fun loadNotifications(): List<Notification>

}