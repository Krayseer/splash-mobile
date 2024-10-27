package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.ConfigurationAPI
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.repository.ConfigurationRepository

class ConfigurationRepositoryImpl(
    private val configurationAPI: ConfigurationAPI
): ConfigurationRepository {

    override suspend fun loadConfiguration(): Configuration {
        return withContext(Dispatchers.IO) {
            configurationAPI.getUserConfiguration()
        }
    }

}