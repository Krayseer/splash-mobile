package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.context.FilterContext
import ru.anykeyers.partner_app.data.store.FavoriteOrderDao
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.data.mapper.FavoriteOrderMapper.toDomain
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository

/**
 * VM для фрагмента заказов
 */
class OrderViewModel(
    private val orderRepository: IOrderRepository,
    private val configurationRepository: IConfigurationRepository,
    private val filterDataStore: OrderFilterDataStore,
    private val filterContext: FilterContext,
    private val favoriteOrderDao: FavoriteOrderDao
): HandlingViewModel() {

    private var _orders: MutableLiveData<List<Order>> = MutableLiveData()

    private var _hasFilter: MutableLiveData<Boolean> = MutableLiveData()

    private var _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)

    val orders: MutableLiveData<List<Order>> get() = _orders

    val hasFilter: MutableLiveData<Boolean> get() = _hasFilter

    val isFavorite: MutableLiveData<Boolean> get() = _isFavorite

    init {
        notifyChange()
    }

    fun notifyChange() {
        if (_isFavorite.value == true) {
            loadFavorites()
        } else {
            loadOrders()
        }
        loadFilterState()
    }

    fun updateFavorite() {
        _isFavorite.value = _isFavorite.value?.not()
        notifyChange()
    }


    private fun loadOrders() {
        launchWithResultState {
            try {
                val configuration: Configuration = configurationRepository.loadConfiguration()
                val orders = orderRepository.loadCarWashOrders(configuration.id)
                applyFilterIfExists(orders)
            } catch (e: Exception) {
                applyFilterIfExists(listOf())
            }
        }
    }

    private fun loadFavorites() {
        launchWithResultState {
            val orders = favoriteOrderDao.getAllFavorites().map { it.toDomain()}
            applyFilterIfExists(orders)
        }
    }

    private fun loadFilterState() {
        launchWithResultState {
            _hasFilter.value = filterContext.isHasOrderFilter()
        }
    }

    private suspend fun applyFilterIfExists(orders: List<Order>) {
        filterDataStore.getOrderFilter()?.let { filter ->
            val filteredOrders = orders.filter { order ->
                (filter.selectedOrderState == null || order.orderState == filter.selectedOrderState) &&
                        (filter.selectedBoxId == null || order.box.id == filter.selectedBoxId)
            }
            _orders.value = filteredOrders
        } ?: run {
            _orders.value = orders
        }
    }

}