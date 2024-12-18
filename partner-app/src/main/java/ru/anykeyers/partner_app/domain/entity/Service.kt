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
     * Идентификатор автомойки, которой принадлежит услуга
     */
    val carWashId: Long,
    /**
     * Название
     */
    val name: String,
    /**
     * Длительность выполнения
     */
    val duration: Long,
    /**
     * Цега
     */
    val price: Int,
)
