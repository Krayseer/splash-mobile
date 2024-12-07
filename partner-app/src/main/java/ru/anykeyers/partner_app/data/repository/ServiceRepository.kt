package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.ServiceAPI
import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.domain.repository.IServiceRepository

/**
 * Реализация сервиса работы с услугами
 */
class ServiceRepository(
    private val serviceAPI: ServiceAPI
) : IServiceRepository {

    override suspend fun addService(carWashId: Long, service: Service) {
        withContext(Dispatchers.IO) {
            serviceAPI.addService(carWashId, service)
        }
    }

    override suspend fun updateService(service: Service) {
        withContext(Dispatchers.IO) {
            serviceAPI.updateService(service)
        }
    }

    override suspend fun deleteService(serviceId: Long) {
        withContext(Dispatchers.IO) {
            serviceAPI.deleteService(serviceId)
        }
    }

}