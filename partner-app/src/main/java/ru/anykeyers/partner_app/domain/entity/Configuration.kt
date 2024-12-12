package ru.anykeyers.partner_app.domain.entity

/**
 * Конфигурация автомойки
 */
data class Configuration (
    /**
     * Идентификатор автомойки
     */
    val id: Long,
    /**
     * Идентификато пользоваителя хозяина автомойки
     */
    val userId: String,
    /**
     * Данные об организации
     */
    val organizationInfo: OrganizationInfo,
    /**
     * Список услуг
     */
    val services: List<Service>,
    /**
     * Список боксов
     */
    val boxes: List<Box>,
    /**
     * Идентификаторы фотографий
     */
    val photoUrls: List<String>,
    /**
     * Время открытия
     */
    val openTime: String,
    /**
     * Время закрытия
     */
    val closeTime: String,
    /**
     * Адрес
     */
    val address: Address,
    /**
     * Тип обработки заказов
     */
    val orderProcessMode: String,
)