package ru.anykeyers.partner_app.domain.entity.dto

import java.io.File

data class ConfigurationUpdateRequest(
    val organizationInfo: String?,
    val openTime: String?,
    val closeTime: String?,
    val orderProcessMode: String?,
    val photos: List<File>,
    val address: String?,
    val video: File?
)