package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import ru.anykeyers.partner_app.data.remote.ConfigurationAPI
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

class ConfigurationRepository(
    private val configurationAPI: ConfigurationAPI
): IConfigurationRepository {

    override suspend fun loadConfiguration(): Configuration {
        return withContext(Dispatchers.IO) {
            configurationAPI.getUserConfiguration()
        }
    }

    override suspend fun loadConfigurationPdf(): Call<ResponseBody> {
        return withContext(Dispatchers.IO) {
            configurationAPI.downloadPdf()
        }
    }

}