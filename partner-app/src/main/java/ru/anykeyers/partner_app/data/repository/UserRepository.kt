package ru.anykeyers.partner_app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anykeyers.partner_app.data.remote.UserAPI
import ru.anykeyers.partner_app.domain.auth.TokenProvider
import ru.anykeyers.partner_app.domain.entity.User
import ru.anykeyers.partner_app.domain.repository.IUserRepository

/**
 * Реализация сервиса работы с пользователями
 */
class UserRepository(
    private val userAPI: UserAPI,
    private  val tokenProvider: TokenProvider
): IUserRepository {

    override suspend fun loadCurrentUser(): User {
        return withContext(Dispatchers.IO) {
            tokenProvider.refreshToken()
            userAPI.getCurrentUser()
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userAPI.updateUser(user)
        }
    }

}