package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.statistics.StatisticsResponse
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IStatisticsRepository

class StatisticsViewModel(
    private val statisticsRepository: IStatisticsRepository,
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private var _statistics: MutableLiveData<StatisticsResponse> = MutableLiveData()

    val statistics: MutableLiveData<StatisticsResponse> get() = _statistics

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        launchWithResultState  {
            val carWash = configurationRepository.loadConfiguration()
            _statistics.value = statisticsRepository.loadStatistics(carWash.id)
        }
    }


}