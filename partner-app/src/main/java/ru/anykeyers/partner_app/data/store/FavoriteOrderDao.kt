package ru.anykeyers.partner_app.data.store

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anykeyers.partner_app.domain.entity.OrderEntity

/**
 * DAO для работы с избранными заказами
 */
@Dao
interface FavoriteOrderDao {

    /**
     * Получить все избранные заказы
     */
    @Query("SELECT * FROM favorite_orders")
    suspend fun getAllFavorites(): List<OrderEntity>

    /**
     * Проверить, находится ли заказ в избранном
     */
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_orders WHERE id = :orderId)")
    suspend fun isOrderFavorite(orderId: Long): Boolean

    /**
     * Добавить заказ в избранное
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    /**
     * Удалить заказ из избранного
     */
    @Delete
    suspend fun deleteOrder(order: OrderEntity)

}

