package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.dto.ConfigurationUpdateRequest
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * ViewModel для управления конфигурацией компании
 */
class CompanyViewModel(
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private val _configuration by lazy { MutableLiveData<Configuration>() }

    val configuration: LiveData<Configuration> get() = _configuration

    init {
        loadConfiguration()
    }

    /**
     * Загружает текущую конфигурацию из репозитория
     */
    private fun loadConfiguration() {
        launchWithResultState {
            _configuration.value = configurationRepository.loadConfiguration()
        }
    }

    /**
     * Обновляет конфигурацию компании на основе запроса
     */
    fun updateConfiguration(configurationUpdateRequest: ConfigurationUpdateRequest) {
        launchWithResultState {
            configurationRepository.updateConfiguration(configurationUpdateRequest)
        }
    }

}