package ru.anykeyers.partner_app.service

import ru.anykeyers.partner_app.domain.Order

/**
 * Сервис обработки заказов
 */
interface OrderService {

    /**
     * Загрузить список заказов
     *
     * @param carWashId идентификатор автомойки
     */
    fun loadOrders(carWashId: Long) : List<Order>

}