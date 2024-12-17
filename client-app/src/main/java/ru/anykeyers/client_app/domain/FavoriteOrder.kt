package ru.anykeyers.client_app.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.anykeyers.client_app.domain.converters.ServiceConverter

@Entity(tableName = "favorite_orders")
data class FavoriteOrder(
    @PrimaryKey val id: Long,
    val isDone: Boolean,
    val price: Int,
    val time: String,
    val date: String,
    val address: String,
    @TypeConverters(ServiceConverter::class) val services: List<Service>,
    val startTime: Long,
    val endTime: Long,
    val isFavorite: Boolean = false
)