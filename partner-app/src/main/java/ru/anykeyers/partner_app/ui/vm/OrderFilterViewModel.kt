package ru.anykeyers.partner_app.ui.vm

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.OrderFilter
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

class OrderFilterViewModel(
    private val configurationRepository: IConfigurationRepository,
    private val filterDataStore: OrderFilterDataStore
): HandlingViewModel() {

    private var _boxes: MutableLiveData<List<Box>> = MutableLiveData()

    private var _filter: MutableLiveData<OrderFilter> = MutableLiveData()

    val boxes: MutableLiveData<List<Box>> get() = _boxes

    val filter: MutableLiveData<OrderFilter> get() = _filter

    init {
        loadBoxes()
        loadFilter()
    }

    fun saveFilter(filter: OrderFilter, parentFragmentManager: FragmentManager) {
        launchWithResultState {
            filterDataStore.saveOrderFilter(filter)
            parentFragmentManager.setFragmentResult("filterApplied", Bundle.EMPTY)
            parentFragmentManager.popBackStack()
        }
    }

    private fun loadFilter() {
        launchWithResultState {
            _filter.value = filterDataStore.getOrderFilter()
        }
    }

    private fun loadBoxes() {
        launchWithResultState {
            _boxes.value = configurationRepository.loadConfiguration().boxes
        }
    }

}