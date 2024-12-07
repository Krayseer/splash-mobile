package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.StatisticsAPI
import ru.anykeyers.partner_app.domain.entity.statistics.StatisticsResponse
import ru.anykeyers.partner_app.domain.repository.IStatisticsRepository

/**
 * Реализация сервиса работы со статистикой
 */
class StatisticsRepository(
    private val statisticsAPI: StatisticsAPI
) : IStatisticsRepository {

    override suspend fun loadStatistics(carWashId: Long): StatisticsResponse {
        return withContext(Dispatchers.IO) {
            statisticsAPI.getStatistics(carWashId)
        }
    }

}