package ru.anykeyers.partner_app.di

import org.koin.dsl.module
import ru.anykeyers.partner_app.data.repository.ConfigurationRepository
import ru.anykeyers.partner_app.data.repository.OrderRepository
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository

val repositoryModule = module {
    single<IConfigurationRepository> { ConfigurationRepository(get()) }
    single<IOrderRepository> { OrderRepository(get()) }
}