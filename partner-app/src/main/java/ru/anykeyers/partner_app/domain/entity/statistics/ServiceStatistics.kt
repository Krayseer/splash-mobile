package ru.anykeyers.partner_app.domain.entity.statistics

/**
 * Статистика услуги
 */
data class ServiceStatistics(
    /**
     * Название услуги
     */
    val name: String,
    /**
     * Общая сумма на выпонления услуги
     */
    val sum: Int,
    /**
     * Количество, сколько раз была выполнена услуга
     */
    val count: Int
)
