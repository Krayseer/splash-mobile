package ru.anykeyers.client_app.di

import androidx.room.Room
import org.koin.dsl.module
import ru.anykeyers.client_app.data.localDb.FavoriteOrderDatabase
import ru.anykeyers.client_app.data.repository.OrderRepositoryImpl
import ru.anykeyers.client_app.domain.repository.FavoriteOrderRepository
import ru.anykeyers.client_app.domain.repository.OrderRepository

val repositoryModule = module {
    single<OrderRepository> { OrderRepositoryImpl(get()) }

    single<FavoriteOrderRepository> { FavoriteOrderRepository(get()) }
    single {
        Room.databaseBuilder(
            get(),
            FavoriteOrderDatabase::class.java,
            "favorite_orders"
        ).build()
    }

    single { get<FavoriteOrderDatabase>().favoriteOrderDao() }
}