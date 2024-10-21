package ru.anykeyers.client_app.service.impl

import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.service.OrderService

class RamOrderService : OrderService {

    override fun loadOrders(clientId: Long): List<Order> {
        val orders: MutableList<Order> = mutableListOf()
        orders.add(Order(1, true, 1000, "12:30", "12-01-2024"))
        orders.add(Order(2, true, 1350, "12:30", "12-01-2024"))
        orders.add(Order(3, true, 1488, "12:30", "12-01-2024"))
        orders.add(Order(4, true, 1700, "12:30", "12-01-2024"))
        orders.add(Order(5, true, 600, "12:30", "12-01-2024"))
        orders.add(Order(6, false, 100, "12:30", "12-01-2024"))
        orders.add(Order(7, false, 350, "12:30", "12-01-2024"))
        return orders
    }

}