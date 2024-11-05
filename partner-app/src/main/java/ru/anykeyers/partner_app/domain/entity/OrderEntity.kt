package ru.anykeyers.partner_app.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_orders")
data class OrderEntity(
    @PrimaryKey val id: Long,
    val userJson: String,
    val carWashId: Long,
    val boxJson: String,
    val orderState: String,
    val startTime: Long,
    val endTime: Long,
    val servicesJson: String,
    val paymentType: String,
    val price: Int,
    val createdAt: String
)