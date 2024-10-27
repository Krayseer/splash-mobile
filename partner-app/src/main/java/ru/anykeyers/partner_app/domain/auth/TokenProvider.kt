package ru.anykeyers.partner_app.domain.auth

/**
 * Провайдер токена авторизации
 */
class TokenProvider(
    private val keycloakService: AuthService,
    private val realm: String,
    private val username: String,
    private val password: String,
    private val clientId: String,
    private val clientSecret: String
) {
    private var accessToken: String? = null
    private var refreshToken: String? = null

    /**
     * Получить токен
     */
    suspend fun getToken(): String {
        return accessToken ?: refreshToken()
    }

    /**
     * Обновить токен
     */
    suspend fun refreshToken(): String {
        val response = keycloakService.getAccessToken(
            realm = realm,
            username = username,
            password = password,
            clientId = clientId,
            clientSecret = clientSecret
        )

        accessToken = response.accessToken
        refreshToken = response.refreshToken
        return accessToken!!
    }
}