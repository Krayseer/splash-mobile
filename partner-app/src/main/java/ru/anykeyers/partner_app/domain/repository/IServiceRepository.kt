package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Service

/**
 * Сервис обработки услуг
 */
interface IServiceRepository {

    /**
     * Добавить новую услугу
     */
    suspend fun addService(carWashId:Long, service: Service)

    /**
     * Обновить услугу
     */
    suspend fun updateService(service: Service)

    /**
     * Удалить услугу
     */
    suspend fun deleteService(serviceId: Long)

}