package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Invitation
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * ViewModel для работы с работниками
 */
class EmployeeViewModel(
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private val _employees by lazy { MutableLiveData<List<Employee>>() }

    private val _invitations by lazy { MutableLiveData<List<Invitation>>() }

    val employees: LiveData<List<Employee>> get() = _employees

    val invitations: LiveData<List<Invitation>> get() = _invitations

    init {
        loadEmployees()
        loadInvitations()
    }

    /**
     * Загрузка списка работников
     */
    private fun loadEmployees() {
        launchWithResultState {
            _employees.value = configurationRepository.loadEmployees()
        }
    }

    /**
     * Загрузка списка приглашений
     */
    private fun loadInvitations() {
        launchWithResultState {
            _invitations.value = configurationRepository.loadInvitations()
        }
    }

}