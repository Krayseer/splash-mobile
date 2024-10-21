package ru.anykeyers.client_app.service

import ru.anykeyers.client_app.domain.Order

interface OrderService {

    fun loadOrders(clientId: Long) : List<Order>

}