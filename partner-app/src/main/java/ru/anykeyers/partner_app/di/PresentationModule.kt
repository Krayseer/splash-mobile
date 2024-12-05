package ru.anykeyers.partner_app.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anykeyers.partner_app.ui.vm.AccountViewModel
import ru.anykeyers.partner_app.ui.vm.ConfigurationViewModel
import ru.anykeyers.partner_app.ui.vm.OrderDetailsViewModel
import ru.anykeyers.partner_app.ui.vm.OrderFilterViewModel
import ru.anykeyers.partner_app.ui.vm.OrderViewModel
import ru.anykeyers.partner_app.ui.vm.StatisticsViewModel

/**
 * Модуль ViewModel представлений
 */
val presentationModule = module {

    viewModel { ConfigurationViewModel(get()) }
    viewModel { OrderViewModel(get(), get(), get(), get(), get()) }
    viewModel { OrderFilterViewModel(get(), get()) }
    viewModel { (orderId: Long) -> OrderDetailsViewModel(orderId, get()) }
    viewModel { AccountViewModel(get(), androidContext()) }
    viewModel { StatisticsViewModel(get(), get()) }

}