package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.context.FilterContext
import ru.anykeyers.partner_app.data.store.FavoriteOrderDao
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.data.mapper.FavoriteOrderMapper.toDomain
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.entity.OrderFilter
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository

/**
 * ViewModel для управления заказами
 */
class OrderViewModel(
    private val orderRepository: IOrderRepository,
    private val configurationRepository: IConfigurationRepository,
    private val filterDataStore: OrderFilterDataStore,
    private val filterContext: FilterContext,
    private val favoriteOrderDao: FavoriteOrderDao
) : HandlingViewModel() {

    private val _orders by lazy { MutableLiveData<List<Order>>() }
    val orders: LiveData<List<Order>> get() = _orders

    private val _hasFilter by lazy { MutableLiveData<Boolean>() }
    val hasFilter: LiveData<Boolean> get() = _hasFilter

    private val _isFavorite by lazy { MutableLiveData(false) }
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    init {
        reloadOrders()
    }

    /**
     * Переключает состояние избранного и перезагружает заказы
     */
    fun toggleFavorite() {
        _isFavorite.value = _isFavorite.value?.not()
        reloadOrders()
    }

    /**
     * Перезагружает список заказов и состояние фильтра
     */
    private fun reloadOrders() {
        if (_isFavorite.value == true) {
            loadFavorites()
        } else {
            loadOrders()
        }
        loadFilterState()
    }

    private fun loadOrders() {
        launchWithResultState {
            try {
                val configuration = configurationRepository.loadConfiguration()
                val orders = orderRepository.loadCarWashOrders(configuration.id)
                applyFilterIfExists(orders)
            } catch (e: Exception) {
                _orders.value = emptyList()
            }
        }
    }

    private fun loadFavorites() {
        launchWithResultState {
            val favoriteOrders = favoriteOrderDao.getAllFavorites().map { it.toDomain() }
            applyFilterIfExists(favoriteOrders)
        }
    }

    private fun loadFilterState() {
        launchWithResultState {
            _hasFilter.value = filterContext.isHasOrderFilter()
        }
    }

    private suspend fun applyFilterIfExists(orders: List<Order>) {
        val filter = filterDataStore.getOrderFilter()
        _orders.value = filter?.let { applyFilter(orders, it) } ?: orders
    }

    /**
     * Применяет фильтр к списку заказов
     */
    private fun applyFilter(orders: List<Order>, filter: OrderFilter): List<Order> {
        return orders.filter { order ->
            (filter.selectedOrderState == null || order.orderState == filter.selectedOrderState) &&
                    (filter.selectedBoxId == null || order.box.id == filter.selectedBoxId)
        }
    }

}
