package ru.anykeyers.partner_app.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.anykeyers.partner_app.domain.entity.Service

interface ServiceAPI {

    @POST("${WebConstant.SERVICE_OF_SERVICES_URL}/{carWashId}")
    suspend fun addService(@Path("carWashId") carWashId: Long, @Body service: Service)

    @PUT(WebConstant.SERVICE_OF_SERVICES_URL)
    suspend fun updateService(@Body service: Service) {
    }

    @DELETE(WebConstant.SERVICE_OF_SERVICES_URL)
    suspend fun deleteService(@Path("serviceId") serviceId: Long) {
    }

}