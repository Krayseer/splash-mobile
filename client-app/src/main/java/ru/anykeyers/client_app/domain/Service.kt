package ru.anykeyers.client_app.domain

import java.io.Serializable

data class Service (

    val name: String,
    val price: Int,
    val duration: Int
) : Serializable