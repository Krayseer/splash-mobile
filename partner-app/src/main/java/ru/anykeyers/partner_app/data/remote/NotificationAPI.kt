package ru.anykeyers.partner_app.data.remote

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
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

    /**
     * Удалить уведомление
     */
    @DELETE("${WebConstant.NOTIFICATION_URL}/push/{pushId}")
    suspend fun deleteNotification(@Path("pushId") id: Long)

}