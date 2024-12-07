package ru.anykeyers.partner_app.domain.entity.statistics

/**
 * Статистика автомойки по дате
 */
data class DateStatistics(
    /**
     * Дата
     */
    val date: String,
    /**
     * Количество заказов
     */
    val ordersCount: Int,
    /**
     * Суммарное количество услуг
     */
    val serviceCountSummary: Int,
    /**
     * Общая сумма выполненных услуг
     */
    val servicePriceSummary: Int
)
