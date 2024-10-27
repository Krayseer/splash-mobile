package ru.anykeyers.partner_app.domain.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Перехватчик запросов, обновляющий состояние токена при запросах на Backend
 */
class AuthInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenProvider.getToken() }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            runBlocking { tokenProvider.refreshToken() }
            val newToken = runBlocking { tokenProvider.getToken() }
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $newToken")
                .build()
            return chain.proceed(newRequest)
        }

        return response
    }

}
