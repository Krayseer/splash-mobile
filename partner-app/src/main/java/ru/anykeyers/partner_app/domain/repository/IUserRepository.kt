package ru.anykeyers.partner_app.domain.repository

import ru.anykeyers.partner_app.domain.entity.User

/**
 * Сервис обработки пользователей
 */
interface IUserRepository {

    /**
     * Загрузить текущего пользователя
     */
    suspend fun loadCurrentUser(): User

    /**
     * Обновить пользователя
     */
    suspend fun updateUser(user: User)

}