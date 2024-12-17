package ru.anykeyers.client_app.domain.auth

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("refresh_expires_in") val refreshExpiresIn: Long
)