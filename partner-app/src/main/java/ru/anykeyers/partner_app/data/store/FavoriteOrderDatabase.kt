package ru.anykeyers.partner_app.data.store

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anykeyers.partner_app.data.converter.FavoriteOrderConverter
import ru.anykeyers.partner_app.domain.entity.OrderEntity

/**
 * БД избранных заказов
 */
@Database(entities = [OrderEntity::class], version = 1)
@TypeConverters(FavoriteOrderConverter::class)
abstract class FavoriteOrderDatabase : RoomDatabase() {

    abstract fun orderDao(): FavoriteOrderDao

}