package ru.anykeyers.partner_app.domain.entity

/**
 * Пользователь
 */
data class User (
    /**
     * Идентификатор
     */
    val id: String,
    /**
     * Имя пользователя
     */
    val username: String
)