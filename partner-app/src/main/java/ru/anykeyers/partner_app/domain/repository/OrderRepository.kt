package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Order

/**
 * Сервис обработки заказов
 */
interface OrderRepository {

    /**
     * Загрузить список заказов
     *
     * @param carWashId идентификатор автомойки
     */
    suspend fun loadCarWashOrders(carWashId: Long) : List<Order>

}