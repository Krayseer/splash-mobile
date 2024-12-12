package ru.anykeyers.partner_app.domain.entity

/**
 * Роль
 */
enum class Role(val localizeName: String) {

    /**
     * Пользователь
     */
    USER("Пользователь"),

    /**
     * Мойщик
     */
    WASHER("Мойщик"),

    /**
     * Менеджер
     */
    MANAGER("Менеджер"),

    /**
     * Владелец автомойки
     */
    HOLDER("Владелец"),

}