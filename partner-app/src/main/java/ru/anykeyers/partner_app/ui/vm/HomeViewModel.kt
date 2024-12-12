package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository

/**
 * ViewModel работы с домашним фрагментом
 */
class HomeViewModel(
    private val configurationRepository: IConfigurationRepository,
    private val orderRepository: IOrderRepository
) : HandlingViewModel() {

    private val _ordersCount by lazy { MutableLiveData<Map<Order.State, Long>>() }

    val ordersCount: MutableLiveData<Map<Order.State, Long>> get() = _ordersCount

    init {
        loadOrdersCount()
    }

    private fun loadOrdersCount() {
        launchWithResultState {
            val configuration = configurationRepository.loadConfiguration()
            _ordersCount.value = orderRepository.loadOrdersCount(configuration.id)
        }
    }

}