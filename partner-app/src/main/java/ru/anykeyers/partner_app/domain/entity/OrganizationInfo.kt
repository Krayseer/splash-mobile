package ru.anykeyers.partner_app.domain.entity

/**
 * Данные об организации
 */
data class OrganizationInfo(
    /**
     * ИНН
     */
    val tin: String,
    /**
     * Тип организации
     */
    val type: String,
    /**
     * Почта
     */
    val email: String,
    /**
     * Название
     */
    val name: String,
    /**
     * Описание
     */
    val description: String,
    /**
     * Телефонный номер
     */
    val phoneNumber: String
)
