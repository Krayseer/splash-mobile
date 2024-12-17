package ru.anykeyers.client_app.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.anykeyers.client_app.domain.Order

interface OrderAPI {

    @GET("${APIConstants.ORDER_SERVICE_URL}/user/active")
    suspend fun getOrdersForClient(): List<Order>

}