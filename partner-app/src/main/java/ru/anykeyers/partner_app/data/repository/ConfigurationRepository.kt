package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.ConfigurationAPI
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Invitation
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.entity.dto.ConfigurationUpdateRequest
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * Реализация сервиса работы с конфигурациями автомоек
 */
class ConfigurationRepository(
    private val configurationAPI: ConfigurationAPI
): IConfigurationRepository {

    override suspend fun loadConfiguration(): Configuration {
        return withContext(Dispatchers.IO) {
            configurationAPI.getUserConfiguration()
        }
    }

    override suspend fun updateConfiguration(configurationUpdateRequest: ConfigurationUpdateRequest) {
        return withContext(Dispatchers.IO) {
            val fields = mapOf<String, String>(
                "organizationInfo" to (configurationUpdateRequest.organizationInfo ?: ""),
                "openTime" to (configurationUpdateRequest.openTime ?: ""),
                "closeTime" to (configurationUpdateRequest.closeTime ?: ""),
                "orderProcessMode" to (configurationUpdateRequest.orderProcessMode ?: ""),
                "address" to (configurationUpdateRequest.address ?: "")
            )
            configurationAPI.updateConfiguration(fields)
        }
    }

    override suspend fun loadEmployees(): List<Employee> {
        return withContext(Dispatchers.IO) {
            configurationAPI.getCarWashEmployees()
        }
    }

    override suspend fun loadInvitations(): List<Invitation> {
        return withContext(Dispatchers.IO) {
            configurationAPI.getCarWashInvitations()
        }
    }

    override suspend fun addBox(box: Box) {
        withContext(Dispatchers.IO) {
            configurationAPI.addBox(box)
        }
    }

    override suspend fun updateBox(box: Box) {
        withContext(Dispatchers.IO) {
            configurationAPI.updateBox(box)
        }
    }

    override suspend fun deleteBox(boxId: Long) {
        withContext(Dispatchers.IO) {
            configurationAPI.deleteBox(boxId)
        }
    }

}