package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Базовый класс для моделей представления, которые подразумевают в себе вызовы сторонних API для
 * получения результата
 */
open class HandlingViewModel: ViewModel() {

    /**
     * Загрузить данные с орбаботкой ошибок и состояния загрузки
     */
    fun <T> ViewModel.launchWithResultState(action: suspend () -> T): LiveData<ResultState<T>> {
        val resultState = MutableLiveData<ResultState<T>>()
        viewModelScope.launch {
            resultState.value = ResultState.Loading
            try {
                resultState.value = ResultState.Success(action())
            } catch (e: Exception) {
                resultState.value = ResultState.Error("Ошибка: ${e.message}")
            }
        }
        return resultState
    }

    /**
     * Результат выполнения запроса
     */
    sealed class ResultState<out T> {
        data object Loading : ResultState<Nothing>()
        data class Success<out T>(val data: T) : ResultState<T>()
        data class Error(val message: String) : ResultState<Nothing>()
    }

}