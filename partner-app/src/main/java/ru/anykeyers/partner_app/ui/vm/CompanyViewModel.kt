package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.dto.ConfigurationUpdateRequest
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

class CompanyViewModel(
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private var _configuration: MutableLiveData<Configuration> = MutableLiveData()

    val configuration: MutableLiveData<Configuration> get() = _configuration

    init {
        loadConfiguration()
    }

    private fun loadConfiguration() {
        launchWithResultState  {
            _configuration.value = configurationRepository.loadConfiguration()
        }
    }

    fun updateConfiguration(configurationUpdateRequest: ConfigurationUpdateRequest) {
        launchWithResultState {
            configurationRepository.updateConfiguration(configurationUpdateRequest)
        }
    }

}