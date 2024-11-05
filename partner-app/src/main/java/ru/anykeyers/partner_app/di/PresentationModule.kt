package ru.anykeyers.partner_app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anykeyers.partner_app.ui.vm.ConfigurationViewModel
import ru.anykeyers.partner_app.ui.vm.OrderDetailsViewModel
import ru.anykeyers.partner_app.ui.vm.OrderFilterViewModel
import ru.anykeyers.partner_app.ui.vm.OrderViewModel

val presentationModule = module {
    viewModel { ConfigurationViewModel(get()) }
    viewModel { OrderViewModel(get(), get(), get(), get(), get()) }
    viewModel { OrderFilterViewModel(get(), get()) }
    viewModel { (orderId: Long) -> OrderDetailsViewModel(orderId, get()) }
}