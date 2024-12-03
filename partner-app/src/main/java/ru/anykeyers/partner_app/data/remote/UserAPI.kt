package ru.anykeyers.partner_app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import ru.anykeyers.partner_app.domain.entity.User

/**
 * API для работы с удаленным сервисом обработки пользователей
 */
interface UserAPI {

    /**
     * Получить список заказов автомойки
     *
     * @param id идентификатор автомойки
     */
    @GET(WebConstant.USER_SERVICE_URL)
    suspend fun getCurrentUser(): User

    /**
     * Обновить пользователя
     *
     * @param user обновленный пользователь
     */
    @PUT(WebConstant.USER_SERVICE_URL)
    suspend fun updateUser(@Body user: User)

}