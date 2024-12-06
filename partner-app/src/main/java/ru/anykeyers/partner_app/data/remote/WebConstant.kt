package ru.anykeyers.partner_app.data.remote

/**
 * Констаны для работы с Web
 */
object WebConstant {

    /**
     * Базовый URL backend
     */
    const val BASE_URL: String = "http://10.0.2.2:8070/api/"

    /**
     * URL сервера KEYCLOAK
     */
    const val KEYCLOAK_BASE_URL: String = "http://192.168.24.188"

    /**
     * URL сервиса обработки конфигураций автомоек
     */
    const val CAR_WASH_SERVICE_URL: String = "${BASE_URL}car-wash"

    /**
     * URL сервиса обработки услуг
     */
    const val SERVICE_OF_SERVICES_URL: String = "${BASE_URL}service"

    /**
     * URL сервиса обработки заказов
     */
    const val ORDER_SERVICE_URL: String = "${BASE_URL}order"

    /**
     * URL сервиса статистики
     */
    const val STATISTICS_SERVICE_URL: String = "${BASE_URL}statistics"

    /**
     * URL сервиса обработки пользователей
     */
    const val USER_SERVICE_URL: String = "${BASE_URL}user"

}