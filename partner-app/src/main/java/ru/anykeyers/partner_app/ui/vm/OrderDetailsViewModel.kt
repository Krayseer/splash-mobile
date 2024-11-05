package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.store.FavoriteOrderDao
import ru.anykeyers.partner_app.data.mapper.FavoriteOrderMapper.toEntity
import ru.anykeyers.partner_app.domain.entity.Order

class OrderDetailsViewModel(
    private val orderId: Long,
    private val favoriteOrderDao: FavoriteOrderDao
): HandlingViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()

    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        checkIsFavorite()
    }

    private fun checkIsFavorite() {
        launchWithResultState {
            _isFavorite.value = favoriteOrderDao.isOrderFavorite(orderId)
        }
    }

    /**
     * Добавить избранный заказ
     */
    fun addFavorite(order: Order) {
        launchWithResultState {
            favoriteOrderDao.insertOrder(order.toEntity())
            _isFavorite.value = true
        }
    }

    /**
     * Удалить избранный заказ
     */
    fun deleteFavorite(order: Order) {
        launchWithResultState {
            favoriteOrderDao.deleteOrder(order.toEntity())
            _isFavorite.value = false
        }
    }

}