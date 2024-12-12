package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.User
import ru.anykeyers.partner_app.domain.repository.IUserRepository

/**
 * ViewModel для работы с фрагментом пользователя
 */
class AccountViewModel(
    private val userRepository: IUserRepository,
) : HandlingViewModel() {

    private val _user by lazy { MutableLiveData<User>() }

    val user: LiveData<User> get() = _user

    init {
        loadUser()
    }

    /**
     * Обновление информации о пользователе
     */
    fun updateUser(user: User) {
        launchWithResultState {
            userRepository.updateUser(user)
        }
    }

    /**
     * Загрузка текущего пользователя из репозитория
     */
    private fun loadUser() {
        launchWithResultState {
            _user.value = userRepository.loadCurrentUser()
        }
    }

}