package ru.anykeyers.partner_app.data.context

import ru.anykeyers.partner_app.data.store.OrderFilterDataStore

/**
 * Контекст для работы с фильтрами приложения
 */
class FilterContext (
    private val orderFilterDataStore: OrderFilterDataStore
) {

    /**
     * Существует ли фильтр по заказам
     */
    suspend fun isHasOrderFilter(): Boolean {
        val orderFilter = orderFilterDataStore.getOrderFilter()
        return orderFilter?.let {
            it.selectedOrderState != null || it.selectedBoxId != null
        } ?: false
    }


}