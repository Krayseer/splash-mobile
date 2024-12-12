package ru.anykeyers.partner_app.domain.entity

/**
 * Адрес
 */
data class Address(
    /**
     * Широта
     */
    val longitude: Double?,
    /**
     * Долгота
     */
    val latitude: Double?,
    /**
     * Адрес
     */
    val address: String?
)