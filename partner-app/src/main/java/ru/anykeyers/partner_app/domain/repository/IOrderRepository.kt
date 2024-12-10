package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Order

/**
 * Сервис обработки заказов
 */
interface IOrderRepository {

    /**
     * Загрузить список заказов
     *
     * @param carWashId идентификатор автомойки
     */
    suspend fun loadCarWashOrders(carWashId: Long) : List<Order>

    /**
     * Загрузить количество заказов по статусам
     *
     * @param id идентификатор автомойки
     */
    suspend fun loadOrdersCount(id: Long): Map<Order.State, Long>

}