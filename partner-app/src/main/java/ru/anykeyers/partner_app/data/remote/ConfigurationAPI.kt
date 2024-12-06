package ru.anykeyers.partner_app.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Invitation

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
     * Получить список работников автомойки
     */
    @GET("${WebConstant.CAR_WASH_SERVICE_URL}/employee")
    suspend fun getCarWashEmployees(): List<Employee>

    /**
     * Получить список отправленных автомойкой приглашений
     */
    @GET("${WebConstant.CAR_WASH_SERVICE_URL}/invitation/by-holder")
    suspend fun getCarWashInvitations(): List<Invitation>

    /**
     * Добавить новый бокс
     */
    @POST("${WebConstant.CAR_WASH_SERVICE_URL}/box")
    suspend fun addBox(@Body box: Box)

    /**
     * Обновить бокс
     */
    @PUT("${WebConstant.CAR_WASH_SERVICE_URL}/box")
    suspend fun updateBox(@Body box: Box)

    /**
     * Удалить бокс
     */
    @DELETE("${WebConstant.CAR_WASH_SERVICE_URL}/box/{boxId}")
    suspend fun deleteBox(@Path("boxId") boxId: Long)

}