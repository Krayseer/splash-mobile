package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * ViewModel для фрагмента "Услуги и боксы"
 */
class ConfigurationViewModel(
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private val _configuration by lazy { MutableLiveData<Configuration>() }

    val configuration: MutableLiveData<Configuration> get() = _configuration

    init {
        loadConfiguration()
    }

    /**
     * Добавление нового бокса.
     */
    fun addBox(box: Box) {
        launchWithResultState {
            configurationRepository.addBox(box)
        }
    }

    /**
     * Обновление информации о боксе
     */
    fun updateBox(box: Box) {
        launchWithResultState {
            configurationRepository.updateBox(box)
        }
    }

    /**
     * Удаление бокса по идентификатору
     */
    fun deleteBox(boxId: Long) {
        launchWithResultState {
            configurationRepository.deleteBox(boxId)
        }
    }

    /**
     * Загрузка конфигурации из репозитория
     */
    private fun loadConfiguration() {
        launchWithResultState {
            _configuration.value = configurationRepository.loadConfiguration()
        }
    }

}