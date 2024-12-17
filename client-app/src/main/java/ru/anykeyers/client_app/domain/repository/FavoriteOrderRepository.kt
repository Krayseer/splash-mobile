package ru.anykeyers.client_app.domain.repository

import ru.anykeyers.client_app.data.localDb.FavoriteOrderDao
import ru.anykeyers.client_app.domain.FavoriteOrder

class FavoriteOrderRepository(private val favoriteOrderDao: FavoriteOrderDao) {
    suspend fun addFavoriteOrder(favoriteOrder: FavoriteOrder) {
        favoriteOrderDao.insert(favoriteOrder)
    }

    suspend fun removeFavoriteOrder(orderId: Long) {
        favoriteOrderDao.deleteById(orderId)
    }

    suspend fun getFavoriteOrderById(orderId: Long): FavoriteOrder? {
        return favoriteOrderDao.getFavoriteOrderById(orderId)
    }

    suspend fun getAllFavoriteOrders(): List<FavoriteOrder> {
        return favoriteOrderDao.getAllFavoriteOrders()
    }
}