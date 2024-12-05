package ru.anykeyers.partner_app.domain.entity.statistics

data class DateStatistics(
    val date: String,
    val ordersCount: Int,
    val serviceCountSummary: Int,
    val servicePriceSummary: Int
)
