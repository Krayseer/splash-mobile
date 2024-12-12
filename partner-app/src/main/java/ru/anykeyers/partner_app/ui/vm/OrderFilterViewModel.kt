package ru.anykeyers.partner_app.ui.vm

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.OrderFilter
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * ViewModel для управления фильтром заказов
 */
class OrderFilterViewModel(
    private val configurationRepository: IConfigurationRepository,
    private val filterDataStore: OrderFilterDataStore
) : HandlingViewModel() {

    private val _boxes by lazy { MutableLiveData<List<Box>>() }

    private val _filter by lazy { MutableLiveData<OrderFilter>() }

    val boxes: LiveData<List<Box>> get() = _boxes

    val filter: LiveData<OrderFilter> get() = _filter

    init {
        fetchBoxes()
        fetchFilter()
    }

    /**
     * Сохраняет фильтр и возвращает результат во фрагменте
     */
    fun applyFilter(filter: OrderFilter, fragmentManager: FragmentManager) {
        launchWithResultState {
            filterDataStore.saveOrderFilter(filter)
            fragmentManager.setFragmentResult("filterApplied", Bundle.EMPTY)
            fragmentManager.popBackStack()
        }
    }

    /**
     * Загружает текущий фильтр из хранилища
     */
    private fun fetchFilter() {
        launchWithResultState {
            _filter.value = filterDataStore.getOrderFilter()
        }
    }

    /**
     * Загружает список боксов из репозитория
     */
    private fun fetchBoxes() {
        launchWithResultState {
            _boxes.value = configurationRepository.loadConfiguration().boxes
        }
    }
}
