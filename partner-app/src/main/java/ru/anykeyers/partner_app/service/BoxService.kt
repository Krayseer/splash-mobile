package ru.anykeyers.partner_app.service

import ru.anykeyers.partner_app.domain.Box

/**
 * Сервис боксов
 */
interface BoxService {

    /**
     * Загрузить список боксов
     *
     * @param carWashId идентификатор автомойки
     */
    fun loadBoxes(carWashId: Long): List<Box>

}