package ru.anykeyers.partner_app.di

import org.koin.dsl.module
import ru.anykeyers.partner_app.data.repository.ConfigurationRepositoryImpl
import ru.anykeyers.partner_app.data.repository.OrderRepositoryImpl
import ru.anykeyers.partner_app.domain.repository.ConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.OrderRepository

val repositoryModule = module {
    single<ConfigurationRepository> { ConfigurationRepositoryImpl(get()) }
    single<OrderRepository> { OrderRepositoryImpl(get()) }
}