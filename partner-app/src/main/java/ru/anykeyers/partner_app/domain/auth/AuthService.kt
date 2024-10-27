package ru.anykeyers.partner_app.domain.auth

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Сервис авторизации
 */
interface AuthService {

    @POST("realms/{realm}/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Path("realm") realm: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "password"
    ): Token

}