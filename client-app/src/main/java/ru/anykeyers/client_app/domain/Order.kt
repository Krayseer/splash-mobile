package ru.anykeyers.client_app.domain

data class Order (

    val id: Long,

    val isDone: Boolean,

    val price: Int,

    val time: String,

    val date: String,

    val address: String,

    val services: List<Service>

)