package ru.anykeyers.client_app.domain.repository

import ru.anykeyers.client_app.domain.Order

interface OrderRepository {

    suspend fun loadOrders(): List<Order>

}