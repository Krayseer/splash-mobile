package ru.anykeyers.partner_app.data.remote

import retrofit2.http.GET
import ru.anykeyers.partner_app.domain.entity.Configuration

/**
 * API для работы с удаленным сервисом обработки конфигураций автомоек
 */
interface ConfigurationAPI {

    /**
     * Получить конфигурацию автомойки авторизованного(текущего) пользователя
     */
    @GET("${WebConstant.CAR_WASH_SERVICE_URL}/configuration")
    suspend fun getUserConfiguration(): Configuration

}