package ru.anykeyers.partner_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.anykeyers.partner_app.domain.Box
import ru.anykeyers.partner_app.service.BoxService
import ru.anykeyers.partner_app.service.impl.InMemoryBoxService

class BoxViewModel : ViewModel() {

    private val boxService: BoxService = InMemoryBoxService()

    private val _boxes = MutableLiveData<List<Box>>()

    val boxes: LiveData<List<Box>> get() = _boxes

    init {
        loadBoxes()
    }

    private fun loadBoxes() {
        _boxes.value = boxService.loadBoxes(1)
    }

}