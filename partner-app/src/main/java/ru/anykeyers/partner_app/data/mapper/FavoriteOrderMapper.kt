package ru.anykeyers.partner_app.data.mapper

import ru.anykeyers.partner_app.data.converter.FavoriteOrderConverter
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.entity.OrderEntity

/**
 * Маппер избранных заказов
 */
object FavoriteOrderMapper {

    private val converter: FavoriteOrderConverter = FavoriteOrderConverter()

    /**
     * Конвертировать заказ в сущность
     */
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

    /**
     * Конвертировать сущность в доменную модель
     */
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