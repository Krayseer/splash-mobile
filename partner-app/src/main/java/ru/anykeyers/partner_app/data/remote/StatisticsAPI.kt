package ru.anykeyers.partner_app.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.anykeyers.partner_app.domain.entity.statistics.StatisticsResponse

/**
 * API для работы с удаленным сервисом статистики
 */
interface StatisticsAPI {

    /**
     * Получить статистику автомойки
     *
     * @param id идентификатор автомойки
     */
    @GET("${WebConstant.STATISTICS_SERVICE_URL}/{carWashId}")
    suspend fun getStatistics(@Path("carWashId") id: Long): StatisticsResponse

}