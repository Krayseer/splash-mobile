package ru.anykeyers.client_app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anykeyers.client_app.viewModel.OrderViewModel

val uiModule = module {
    viewModel {OrderViewModel(get(), get())}
}