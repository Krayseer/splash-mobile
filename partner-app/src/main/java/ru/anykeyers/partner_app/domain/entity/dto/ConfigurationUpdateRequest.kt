package ru.anykeyers.partner_app.domain.entity.dto

import java.io.File

/**
 * DTO для обновления автомойки
 */
data class ConfigurationUpdateRequest(
    /**
     * Информация об организации
     */
    val organizationInfo: String?,
    /**
     * Время открытия
     */
    val openTime: String?,
    /**
     * Время закрытия
     */
    val closeTime: String?,
    /**
     * Тип обработки заказов
     */
    val orderProcessMode: String?,
    /**
     * Список фотографий
     */
    val photos: List<File>,
    /**
     * Адрес
     */
    val address: String?,
    /**
     * Видеоролик
     */
    val video: File?
)