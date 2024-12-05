package ru.anykeyers.partner_app.domain.entity.statistics

data class SummaryStatistics(
    val summaryCount: Int,
    val summaryPrice: Int,
    val services: List<ServiceStatistics>
)
