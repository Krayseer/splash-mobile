package ru.anykeyers.client_app.domain

data class Profile(
    val fullName: String,
    val phoneNumber: String,
    val avatarUri: String,
    val resumeUrl: String,
    val timeNotification: String
)