package ru.anykeyers.client_app.domain.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.anykeyers.client_app.domain.Service

class ServiceConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromServicesList(services: List<Service>?): String? {
        return gson.toJson(services) // Конвертируем список в строку (JSON)
    }

    @TypeConverter
    fun toServicesList(servicesString: String?): List<Service>? {
        val listType = object : TypeToken<List<Service>>() {}.type
        return gson.fromJson(servicesString, listType) // Конвертируем строку обратно в список
    }
}