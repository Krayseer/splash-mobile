package ru.anykeyers.partner_app.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.anykeyers.partner_app.data.repository.ConfigurationRepository
import ru.anykeyers.partner_app.data.repository.OrderRepository
import ru.anykeyers.partner_app.data.repository.ServiceRepository
import ru.anykeyers.partner_app.data.repository.StatisticsRepository
import ru.anykeyers.partner_app.data.repository.UserRepository
import ru.anykeyers.partner_app.data.store.FavoriteOrderDatabase
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository
import ru.anykeyers.partner_app.domain.repository.IServiceRepository
import ru.anykeyers.partner_app.domain.repository.IStatisticsRepository
import ru.anykeyers.partner_app.domain.repository.IUserRepository

/**
 * Модуль для работы с DAO
 */
val repositoryModule = module {

    single<IConfigurationRepository> { ConfigurationRepository(get()) }
    single<IOrderRepository> { OrderRepository(get()) }
    single<IUserRepository> { UserRepository(get(), get()) }
    single<IStatisticsRepository> { StatisticsRepository(get()) }
    single<IServiceRepository> { ServiceRepository(get()) }

    single<OrderFilterDataStore> { OrderFilterDataStore(androidContext()) }
    single {
        Room.databaseBuilder(
            get(),
            FavoriteOrderDatabase::class.java,
            "favorite_orders"
        ).build()
    }

    single { get<FavoriteOrderDatabase>().orderDao() }

}