package ru.anykeyers.partner_app.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Токен
 */
data class Token (
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("refresh_expires_in") val refreshExpiresIn: Long
)