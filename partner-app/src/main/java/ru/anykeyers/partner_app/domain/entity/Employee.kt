package ru.anykeyers.partner_app.domain.entity

/**
 * Работник
 */
data class Employee(
    /**
     * Данные пользователя
     */
    val user: User,
    /**
     * Статус работника
     */
    val status: Status,
    /**
     * Автомойка, которой принадлежит работник
     */
    val configuration: Configuration
) {
    /**
     * Статус работника
     */
    enum class Status(val localizeName: String) {
        /**
         * Активный
         */
        ACTIVE("Активен"),

        /**
         * Неактивный
         */
        NON_ACTIVE("Не активен"),
        /**
         * В отпуске
         */
        VACATION("В отпуске")
    }
}
