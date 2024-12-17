package ru.anykeyers.client_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anykeyers.client_app.domain.FavoriteOrder
import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.domain.repository.FavoriteOrderRepository
import ru.anykeyers.client_app.domain.repository.OrderRepository

class OrderViewModel(private val orderRepository: OrderRepository,
                     private val favoriteOrderRepository: FavoriteOrderRepository): ViewModel() {

    private val _orders: MutableLiveData<List<Order>> = MutableLiveData()
    val orders: LiveData<List<Order>> get() = _orders

    private val _favoriteOrders: MutableLiveData<List<FavoriteOrder>> = MutableLiveData()
    val favoriteOrders: LiveData<List<FavoriteOrder>> get() = _favoriteOrders

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    init {
        loadOrders()
        loadFavoriteOrders()
    }

    private fun loadOrders() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val orderList = orderRepository.loadOrders()
                _orders.value = orderList
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Загрузка избранных заказов
    private fun loadFavoriteOrders() {
        viewModelScope.launch {
            try {
                val favoriteList = favoriteOrderRepository.getAllFavoriteOrders()
                _favoriteOrders.value = favoriteList
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки избранных заказов: ${e.message}"
            }
        }
    }

    // Переключение избранного состояния заказа
    fun toggleFavorite(order: Order) {
        viewModelScope.launch {
            try {
                val currentFavorites = favoriteOrders.value ?: emptyList()
                val isFavorite = currentFavorites.any { it.id == order.id }

                if (isFavorite) {
                    favoriteOrderRepository.removeFavoriteOrder(order.id)
                } else {
                    val favoriteOrder = order.toFavoriteOrder()
                    favoriteOrderRepository.addFavoriteOrder(favoriteOrder)
                }

                // Перезагружаем список избранного
                loadFavoriteOrders()
            } catch (e: Exception) {
                _error.value = "Ошибка при изменении избранного состояния: ${e.message}"
            }
        }
    }

    // Преобразование Order в FavoriteOrder
    private fun Order.toFavoriteOrder(): FavoriteOrder {
        return FavoriteOrder(
            id = this.id ?: 0L,
            isDone = this.isDone ?: false,
            price = this.price ?: 0,
            time = this.time ?: "",
            date = this.date ?: "",
            address = this.address ?: "",
            services = this.services ?: emptyList(),
            startTime = this.startTime ?: 0L,
            endTime = this.endTime ?: 0L,
            isFavorite = true
        )
    }
}