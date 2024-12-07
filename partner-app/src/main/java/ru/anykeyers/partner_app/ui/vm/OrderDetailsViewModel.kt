package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.store.FavoriteOrderDao
import ru.anykeyers.partner_app.data.mapper.FavoriteOrderMapper.toEntity
import ru.anykeyers.partner_app.domain.entity.Order

/**
 * ViewModel для работы с деталями заказа
 */
class OrderDetailsViewModel(
    private val orderId: Long,
    private val favoriteOrderDao: FavoriteOrderDao
) : HandlingViewModel() {

    private val _isFavorite by lazy { MutableLiveData<Boolean>() }

    val isFavorite: LiveData<Boolean> get() = _isFavorite

    init {
        checkIsFavorite()
    }

    /**
     * Проверяет, является ли заказ избранным
     */
    private fun checkIsFavorite() {
        launchWithResultState {
            _isFavorite.value = favoriteOrderDao.isOrderFavorite(orderId)
        }
    }

    /**
     * Добавляет заказ в избранное
     */
    fun addFavorite(order: Order) {
        launchWithResultState {
            favoriteOrderDao.insertOrder(order.toEntity())
            _isFavorite.postValue(true)
        }
    }

    /**
     * Удаляет заказ из избранного
     */
    fun deleteFavorite(order: Order) {
        launchWithResultState {
            favoriteOrderDao.deleteOrder(order.toEntity())
            _isFavorite.postValue(false)
        }
    }

}