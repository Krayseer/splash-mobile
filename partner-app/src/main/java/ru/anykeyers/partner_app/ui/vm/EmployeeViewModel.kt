package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Invitation
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * VM для работы с работниками
 */
class EmployeeViewModel(
    private val configurationRepository: IConfigurationRepository
) : HandlingViewModel() {

    private var _employees: MutableLiveData<List<Employee>> = MutableLiveData()

    private val _invitations: MutableLiveData<List<Invitation>> = MutableLiveData()

    val employees: MutableLiveData<List<Employee>> get() = _employees

    val invitations: MutableLiveData<List<Invitation>> get() = _invitations

    init {
        loadEmployees()
        loadInvitations()
    }

    private fun loadEmployees() {
        launchWithResultState  {
            _employees.value = configurationRepository.loadEmployees()
        }
    }

    private fun loadInvitations() {
        launchWithResultState  {
            _invitations.value = configurationRepository.loadInvitations()
        }
    }

}