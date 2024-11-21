package ru.anykeyers.partner_app.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
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

    /**
     * Получить отчет по автомойке текущего пользователя
     */
    @GET("${WebConstant.CAR_WASH_SERVICE_URL}/configuration/pdf")
    fun downloadPdf(): Call<ResponseBody>

}