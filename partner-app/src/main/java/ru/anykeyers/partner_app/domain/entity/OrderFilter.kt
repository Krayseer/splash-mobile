package ru.anykeyers.partner_app.domain.entity

/**
 * Фильтр заказов
 */
data class OrderFilter (
    /**
     * Выбранное состояние заказа
     */
    val selectedOrderState: Order.State?,
    /**
     * Выбранный бокс
     */
    val selectedBoxId: Long?
)