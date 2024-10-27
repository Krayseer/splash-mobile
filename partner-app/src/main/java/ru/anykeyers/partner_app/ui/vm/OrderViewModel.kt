package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.repository.ConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.OrderRepository

/**
 * VM для фрагмента заказов
 */
class OrderViewModel(
    private val orderRepository: OrderRepository,
    private val configurationRepository: ConfigurationRepository
): HandlingViewModel() {

    private var _orders: MutableLiveData<List<Order>> = MutableLiveData()

    val orders: MutableLiveData<List<Order>> get() = _orders

    init {
        loadOrders()
    }

    private fun loadOrders() {
        launchWithResultState {
            val configuration: Configuration = configurationRepository.loadConfiguration()
            _orders.value = orderRepository.loadCarWashOrders(configuration.id)
        }
    }

}