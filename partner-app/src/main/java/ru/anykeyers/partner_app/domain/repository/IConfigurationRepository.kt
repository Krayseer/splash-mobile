package ru.anykeyers.partner_app.domain.repository

import okhttp3.ResponseBody
import retrofit2.Call
import ru.anykeyers.partner_app.domain.entity.Configuration

/**
 * Сервис обработки конфигураций автомоек
 */
interface IConfigurationRepository {

    /**
     * Загрузить автомойку текущего (авторизованного) пользователя
     */
    suspend fun loadConfiguration(): Configuration

    /**
     * Загрузить отчет автомойки текущего пользователя
     */
    suspend fun loadConfigurationPdf(): Call<ResponseBody>

}