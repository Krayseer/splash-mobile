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
     * URL сервиса обработки конфигураций автомоек
     */
    const val CAR_WASH_SERVICE_URL: String = "${BASE_URL}car-wash"

    /**
     * URL сервиса обработки заказов
     */
    const val ORDER_SERVICE_URL: String = "${BASE_URL}order"

}