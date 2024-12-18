package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * VM для фрагмента "Услуги и боксы"
 */
class ConfigurationViewModel(
    private val configurationRepository: IConfigurationRepository
): HandlingViewModel() {

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

}