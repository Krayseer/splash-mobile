package ru.anykeyers.partner_app.domain.entity

/**
 * Приглашение
 */
data class Invitation(
    /**
     * Идентификатор
     */
    val id: Long,
    /**
     * Пользователь, кому отправлено приглашение
     */
    val user: User,
    /**
     * Идентификатор автомойки
     */
    val carWashId: Long,
    /**
     * Список ролей
     */
    val roles: List<Role>,
    /**
     * Статус приглашения
     */
    val invitationState: State
) {
    /**
     * Состояние приглашения
     */
    enum class State(val localizeName: String) {
        /**
         * Отправлен
         */
        SENT("Отправлен"),

        /**
         * Одобрен
         */
        ACCEPTED("Одобрен"),

        /**
         * Отклонен
         */
        REJECTED("Отклонен")
    }
}
