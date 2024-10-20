package ru.anykeyers.partner_app.service.impl

import ru.anykeyers.partner_app.domain.Box
import ru.anykeyers.partner_app.service.BoxService

/**
 * Сервис боксов с использованием локальной памяти
 */
class InMemoryBoxService : BoxService {

    override fun loadBoxes(carWashId: Long): List<Box> {
        val boxList: MutableList<Box> = mutableListOf()
        boxList.add(Box(1, "Бокс 1"))
        boxList.add(Box(2, "Бокс 2"))
        boxList.add(Box(3, "Бокс 3"))
        return boxList
    }

}