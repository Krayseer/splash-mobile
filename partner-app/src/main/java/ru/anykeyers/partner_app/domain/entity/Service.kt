package ru.anykeyers.partner_app.domain.entity

/**
 * Услуга
 */
data class Service(
    /**
     * Идентификатор услуги
     */
    val id: Long,
    /**
     * Название
     */
    val name: String,
    /**
     * Длительность выполнения
     */
    val duration: Long,
    /**
     * Цена
     */
    val price: Int,
)
