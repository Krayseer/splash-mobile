package ru.anykeyers.partner_app.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.anykeyers.partner_app.domain.entity.Service

/**
 * API для работы с удаленным сервисом услуг
 */
interface ServiceAPI {

    /**
     * Добавить услугу
     *
     * @param carWashId идентификатор автомойки
     * @param service   услуга
     */
    @POST("${WebConstant.SERVICE_OF_SERVICES_URL}/{carWashId}")
    suspend fun addService(@Path("carWashId") carWashId: Long, @Body service: Service)

    /**
     * Обновить услугу
     *
     * @param service обновленная услуга
     */
    @PUT(WebConstant.SERVICE_OF_SERVICES_URL)
    suspend fun updateService(@Body service: Service)

    /**
     * Удалить услугу
     *
     * @param serviceId идентификатор услуги
     */
    @DELETE("${WebConstant.SERVICE_OF_SERVICES_URL}/{serviceId}")
    suspend fun deleteService(@Path("serviceId") serviceId: Long)

}