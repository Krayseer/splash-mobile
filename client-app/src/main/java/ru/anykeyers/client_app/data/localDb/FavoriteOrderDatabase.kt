package ru.anykeyers.client_app.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anykeyers.client_app.domain.FavoriteOrder
import ru.anykeyers.client_app.domain.converters.ServiceConverter

@Database(entities = [FavoriteOrder::class], version = 1)
@TypeConverters(ServiceConverter::class)
abstract class FavoriteOrderDatabase : RoomDatabase() {
    abstract fun favoriteOrderDao(): FavoriteOrderDao
}