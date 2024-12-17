package ru.anykeyers.client_app.domain.auth

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("Auth Interceptor", "Intercept called")
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