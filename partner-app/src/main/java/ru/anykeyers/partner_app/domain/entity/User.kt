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
    val username: String,
    /**
     * Данные пользователя
     */
    val userInfo: Info
) {
    data class Info (
        /**
         * Имя
         */
        val firstName: String,
        /**
         * Фамилия
         */
        val lastName: String,
        /**
         * Почта
         */
        val email: String,
        /**
         * Телефонный номер
         */
        val phoneNumber: String,
        /**
         * URL фотографии
         */
        val photoUrl: String,
        /**
         * Список ролей
         */
        val roles: List<String>
    )
}