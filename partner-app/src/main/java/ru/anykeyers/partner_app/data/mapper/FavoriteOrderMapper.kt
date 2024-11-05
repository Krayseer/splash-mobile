package ru.anykeyers.partner_app.data.mapper

import ru.anykeyers.partner_app.data.converter.FavoriteOrderConverter
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.entity.OrderEntity

object FavoriteOrderMapper {

    private val converter: FavoriteOrderConverter = FavoriteOrderConverter()

    fun Order.toEntity(): OrderEntity {
        return OrderEntity(
            id = this.id,
            userJson = converter.fromUser(this.user),
            carWashId = this.carWashId,
            boxJson = converter.fromBox(this.box),
            orderState = this.orderState.name,
            startTime = this.startTime,
            endTime = this.endTime,
            servicesJson = converter.fromServices(this.services),
            paymentType = this.paymentType,
            price = this.price,
            createdAt = this.createdAt
        )
    }

    fun OrderEntity.toDomain(): Order {
        return Order(
            id = this.id,
            user = converter.toUser(this.userJson),
            carWashId = this.carWashId,
            box = converter.toBox(this.boxJson),
            orderState = Order.State.valueOf(this.orderState),
            startTime = this.startTime,
            endTime = this.endTime,
            services = converter.toServices(this.servicesJson),
            paymentType = this.paymentType,
            price = this.price,
            createdAt = this.createdAt
        )
    }

}