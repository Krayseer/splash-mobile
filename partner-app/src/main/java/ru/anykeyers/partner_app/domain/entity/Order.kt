package ru.anykeyers.partner_app.domain.entity

import java.io.Serializable

/**
 * Заказ
 */
class Order(
    /**
     * Идентификатор
     */
    val id: Long,
    /**
     * Пользователь
     */
    val user: User,
    /**
     * Идентификатор автомойки
     */
    val carWashId: Long,
    /**
     * Идентификатор бокса
     */
    val box: Box,
    /**
     * Статус заказа
     */
    val orderState: State,
    /**
     * Время записи
     */
    val startTime: Long,
    /**
     * Время окончания заказа
     */
    val endTime: Long,
    /**
     * Услуги
     */
    val services: List<Service>,
    /**
     * Вид оплаты
     */
    val paymentType: String,
    /**
     * Цена заказа
     */
    val price: Int,
    /**
     * Время создания заказа
     */
    val createdAt: String) : Serializable {

    /**
     * Состояние заказа
     */
    enum class State(val localizeName: String) : Serializable {
        /**
         * Ожидает одобрения
         */
        WAIT_CONFIRM("Ожидает одобрения"),

        /**
         * Ожидает обработки
         */
        WAIT_PROCESS("Ожидает обработки"),

        /**
         * Находится в обработке
         */
        PROCESSING("В обработке"),

        /**
         * Обработан
         */
        PROCESSED("Выполнен")
    }

}