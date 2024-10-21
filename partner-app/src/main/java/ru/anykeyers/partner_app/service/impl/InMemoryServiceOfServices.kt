package ru.anykeyers.partner_app.service.impl

import ru.anykeyers.partner_app.domain.Service
import ru.anykeyers.partner_app.service.ServiceOfServices

class InMemoryServiceOfServices : ServiceOfServices {

    override fun loadServices(carWashId: Long): List<Service> {
        val services: MutableList<Service> = mutableListOf()
        services.add(Service(1, 1, "Пылесос (салон)", 600000, 150))
        services.add(Service(2, 1, "Чернение шин", 650000, 200))
        return services
    }

}