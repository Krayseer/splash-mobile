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
        var firstName: String,
        /**
         * Фамилия
         */
        var lastName: String,
        /**
         * Почта
         */
        var email: String,
        /**
         * Телефонный номер
         */
        var phoneNumber: String,
        /**
         * URL фотографии
         */
        var photoUrl: String?,
        /**
         * Список ролей
         */
        var roles: List<String>
    )
}