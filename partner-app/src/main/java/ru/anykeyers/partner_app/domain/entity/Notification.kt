package ru.anykeyers.partner_app.domain.entity

/**
 * Уведомление
 */
data class Notification(
    /**
     * Идентификатор
     */
    val id: Long,
    /**
     * Тема сообщения
     */
    val subject: String,
    /**
     * Текст сообщения
     */
    val message: String,
    /**
     * Время создания
     */
    val createdAt: String
)
