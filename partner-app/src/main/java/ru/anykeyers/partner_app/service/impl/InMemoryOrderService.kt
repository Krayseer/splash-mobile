package ru.anykeyers.partner_app.service.impl

import ru.anykeyers.partner_app.domain.Order
import ru.anykeyers.partner_app.service.OrderService
import ru.anykeyers.partner_app.service.ServiceOfServices

/**
 * Сервис обработки услуг в локальной памяти
 */
class InMemoryOrderService : OrderService {

    private var serviceOfServices: ServiceOfServices = InMemoryServiceOfServices()

    override fun loadOrders(carWashId: Long): List<Order> {
        val orders: MutableList<Order> = mutableListOf()
        orders.add(Order(4, "Eugene", Order.State.PROCESSED, 1729542930000L, serviceOfServices.loadServices(1), "Бокс 4", 400))
        orders.add(Order(3, "Ivan", Order.State.PROCESSED, 1729543930000L, serviceOfServices.loadServices(1), "Бокс 1", 650))
        orders.add(Order(2, "Dmitry", Order.State.PROCESSED, 1729544930000L, serviceOfServices.loadServices(1), "Бокс 2", 200))
        orders.add(Order(1, "Stanley", Order.State.PROCESSING, 1729545930000L, serviceOfServices.loadServices(1), "Бокс 3", 500))
        return orders
    }

}