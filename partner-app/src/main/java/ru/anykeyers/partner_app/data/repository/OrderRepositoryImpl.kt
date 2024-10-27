package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.OrderAPI
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val orderAPI: OrderAPI
): OrderRepository {

    override suspend fun loadCarWashOrders(carWashId: Long): List<Order> {
        return withContext(Dispatchers.IO) {
            orderAPI.getCarWashOrders(carWashId)
        }
    }

}