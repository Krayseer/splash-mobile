package ru.anykeyers.partner_app.domain.entity.statistics

/**
 * Ответ с информацией о полной статистике автомойки
 */
data class StatisticsResponse(
    /**
     * Общая статистика
     */
    val summaryStatistics: SummaryStatistics,
    /**
     * Статистика по датам
     */
    val dateStatistics: List<DateStatistics>
)
