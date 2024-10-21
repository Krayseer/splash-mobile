package ru.anykeyers.partner_app.service

import ru.anykeyers.partner_app.domain.Service

/**
 * Сервис обработки услуг
 */
interface ServiceOfServices {

    /**
     * Загрузить все услуги
     *
     * @param carWashId идентификатор автомойки
     */
    fun loadServices(carWashId: Long) : List<Service>

}
