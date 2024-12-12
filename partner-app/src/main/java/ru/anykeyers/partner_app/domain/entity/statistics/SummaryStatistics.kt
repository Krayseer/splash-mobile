package ru.anykeyers.partner_app.domain.entity.statistics

/**
 * Суммарная статистика
 */
data class SummaryStatistics(
    /**
     * Суммарное количество выполнения услуг
     */
    val summaryCount: Int,
    /**
     * Общая сумма выполнения услуг
     */
    val summaryPrice: Int,
    /**
     * Общие список всех выполненных услуг
     */
    val services: List<ServiceStatistics>
)
