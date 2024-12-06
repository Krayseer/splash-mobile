package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Invitation
import ru.anykeyers.partner_app.domain.entity.Service

/**
 * Сервис обработки конфигураций автомоек
 */
interface IConfigurationRepository {

    /**
     * Загрузить автомойку текущего (авторизованного) пользователя
     */
    suspend fun loadConfiguration(): Configuration

    /**
     * Загрузить список работников автомойки
     */
    suspend fun loadEmployees(): List<Employee>

    /**
     * Загрузить список приглашений, отправленных владельцем автомойкой
     */
    suspend fun loadInvitations(): List<Invitation>

    /**
     * Добавить новый бокс
     */
    suspend fun addBox(box: Box)

    /**
     * Обновить бокс
     */
    suspend fun updateBox(box: Box)

    /**
     * Удалить бокс
     */
    suspend fun deleteBox(boxId: Long)

}