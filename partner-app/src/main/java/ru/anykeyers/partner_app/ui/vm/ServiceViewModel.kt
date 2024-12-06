package ru.anykeyers.partner_app.ui.vm

import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.domain.repository.IServiceRepository

/**
 * VM для работы с услугами
 */
class ServiceViewModel(
    private val serviceRepository: IServiceRepository
) : HandlingViewModel() {

    fun addService(carWashId: Long, service: Service) {
        launchWithResultState {
            serviceRepository.addService(carWashId, service)
        }
    }

    fun updateService(service: Service) {
        launchWithResultState {
            serviceRepository.updateService(service)
        }
    }

    fun deleteService(serviceId: Long) {
        launchWithResultState {
            serviceRepository.deleteService(serviceId)
        }
    }

}