package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.statistics.StatisticsResponse

/**
 * Сервис загрузки статистики
 */
interface IStatisticsRepository {

    /**
     * Загрузить статистику автомойки
     */
    suspend fun loadStatistics(carWashId: Long): StatisticsResponse

}