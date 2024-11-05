package ru.anykeyers.partner_app.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.anykeyers.partner_app.data.repository.ConfigurationRepository
import ru.anykeyers.partner_app.data.repository.OrderRepository
import ru.anykeyers.partner_app.data.store.FavoriteOrderDatabase
import ru.anykeyers.partner_app.data.store.OrderFilterDataStore
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository
import ru.anykeyers.partner_app.domain.repository.IOrderRepository

val repositoryModule = module {
    single<IConfigurationRepository> { ConfigurationRepository(get()) }
    single<IOrderRepository> { OrderRepository(get()) }

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