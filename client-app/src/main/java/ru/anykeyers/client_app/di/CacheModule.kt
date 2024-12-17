package ru.anykeyers.client_app.di

import org.koin.dsl.module
import ru.anykeyers.client_app.cache.FilterOrdersCache

val cacheModule = module {
    single { FilterOrdersCache() }
}