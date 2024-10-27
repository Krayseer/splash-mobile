package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Configuration

/**
 * Сервис обработки конфигураций автомоек
 */
interface ConfigurationRepository {

    /**
     * Загрузить автомойку текущего (авторизованного) пользователя
     */
    suspend fun loadConfiguration(): Configuration

}