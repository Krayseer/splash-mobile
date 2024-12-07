package ru.anykeyers.partner_app.ui.vm

import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.domain.repository.IServiceRepository

/**
 * ViewModel для работы с услугами
 */
class ServiceViewModel(
    private val serviceRepository: IServiceRepository
) : HandlingViewModel() {

    /**
     * Добавить новую услугу
     */
    fun addService(carWashId: Long, service: Service) {
        launchWithResultState {
            serviceRepository.addService(carWashId, service)
        }
    }

    /**
     * Обновить услугу
     */
    fun updateService(service: Service) {
        launchWithResultState {
            serviceRepository.updateService(service)
        }
    }

    /**
     * Удалить услугу
     */
    fun deleteService(serviceId: Long) {
        launchWithResultState {
            serviceRepository.deleteService(serviceId)
        }
    }

}