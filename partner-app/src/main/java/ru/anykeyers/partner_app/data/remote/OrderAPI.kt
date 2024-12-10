package ru.anykeyers.partner_app.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anykeyers.partner_app.domain.entity.Order

/**
 * API для работы с удаленным сервисом обработки заказов
 */
interface OrderAPI {

    /**
     * Получить список заказов автомойки
     *
     * @param id идентификатор автомойки
     */
    @GET("${WebConstant.ORDER_SERVICE_URL}/car-wash/{carWashId}")
    suspend fun getCarWashOrders(@Path("carWashId") id: Long): List<Order>

    /**
     * Получить количество заказов автомойки по состояниям
     */
    @GET("${WebConstant.ORDER_SERVICE_URL}/car-wash/count")
    suspend fun getCarWashCountOrders(@Query("carWashId") carWashId: Long): Map<Order.State, Long>

}