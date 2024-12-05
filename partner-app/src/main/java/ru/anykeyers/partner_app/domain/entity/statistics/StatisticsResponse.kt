package ru.anykeyers.partner_app.domain.entity.statistics

data class StatisticsResponse(
    val summaryStatistics: SummaryStatistics,
    val dateStatistics: List<DateStatistics>
)
