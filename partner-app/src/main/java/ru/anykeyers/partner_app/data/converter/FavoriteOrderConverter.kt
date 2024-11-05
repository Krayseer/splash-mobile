package ru.anykeyers.partner_app.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.domain.entity.User

class FavoriteOrderConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromUser(user: User): String = gson.toJson(user)

    @TypeConverter
    fun toUser(userJson: String): User = gson.fromJson(userJson, User::class.java)

    @TypeConverter
    fun fromBox(box: Box): String = gson.toJson(box)

    @TypeConverter
    fun toBox(boxJson: String): Box = gson.fromJson(boxJson, Box::class.java)

    @TypeConverter
    fun fromServices(services: List<Service>): String = gson.toJson(services)

    @TypeConverter
    fun toServices(servicesJson: String): List<Service> =
        gson.fromJson(servicesJson, object : TypeToken<List<Service>>() {}.type)

}