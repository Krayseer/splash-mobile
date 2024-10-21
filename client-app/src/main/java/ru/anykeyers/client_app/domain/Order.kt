package ru.anykeyers.client_app.domain

import java.util.Date

data class Order (

    val id: Long,

    val isDone: Boolean,

    val price: Int,

    val time: String,

    val date: String

)