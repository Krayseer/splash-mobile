package ru.anykeyers.partner_app.domain

data class Service(
    val id: Long,
    val carWashId: Long,
    val name: String,
    val duration: Long,
    val price: Int,
)
