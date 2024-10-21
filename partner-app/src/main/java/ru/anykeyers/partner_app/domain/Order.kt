package ru.anykeyers.partner_app.domain

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
    val user: String,
    /**
     * Статус заказа
     */
    val status: State,
    /**
     * Время записи
     */
    val time: Long,
    /**
     * Услуги
     */
    val services: List<Service>,
    /**
     * Бокс, назначенный на заказ
     */
    val box: String,
    /**
     * Цена заказа
     */
    val price: Int) : Serializable {

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