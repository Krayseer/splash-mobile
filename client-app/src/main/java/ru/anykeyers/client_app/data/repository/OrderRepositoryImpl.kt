package ru.anykeyers.client_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.client_app.data.api.OrderAPI
import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.domain.repository.OrderRepository

class OrderRepositoryImpl(private val orderAPI: OrderAPI) : OrderRepository {

    override suspend fun loadOrders(): List<Order> {
        return withContext(Dispatchers.IO) {
            orderAPI.getOrdersForClient()
        }
    }


}