package ru.anykeyers.partner_app.di

import org.koin.dsl.module
import ru.anykeyers.partner_app.data.context.FilterContext

/**
 * Модуль контекстов приложения
 */
val contextModule = module {

    single<FilterContext> { FilterContext(get()) }

}