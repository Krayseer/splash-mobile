package ru.anykeyers.client_app.service.impl

import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.domain.Service
import ru.anykeyers.client_app.service.OrderService

class RamOrderService : OrderService {

    override fun loadOrders(clientId: Long): List<Order> {
        val services: MutableList<Service> = mutableListOf()
        services.add(Service("Чистка колес", 250, 15))
        services.add(Service("Мойка кузова", 500, 10))
        services.add(Service("Полировка пластика салона", 1000, 30))
        services.add(Service("Чистка кожи", 350, 20))

        val orders: MutableList<Order> = mutableListOf()
        orders.add(Order(1, true, 1000, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(2, true, 1350, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(3, true, 1488, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(4, true, 1700, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(5, true, 600, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(6, false, 100, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        orders.add(Order(7, false, 350, "12:30", "12-01-2024", "ул. Большакова, 53", services))
        return orders
    }

}