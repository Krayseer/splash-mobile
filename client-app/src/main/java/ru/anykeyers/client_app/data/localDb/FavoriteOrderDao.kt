package ru.anykeyers.client_app.data.localDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anykeyers.client_app.domain.FavoriteOrder

@Dao
interface FavoriteOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteOrder: FavoriteOrder)

    @Query("DELETE FROM favorite_orders WHERE id = :orderId")
    suspend fun deleteById(orderId: Long)

    @Query("SELECT * FROM favorite_orders WHERE id = :orderId")
    suspend fun getFavoriteOrderById(orderId: Long): FavoriteOrder?

    @Query("SELECT * FROM favorite_orders")
    suspend fun getAllFavoriteOrders(): List<FavoriteOrder>

}