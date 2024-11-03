package ru.anykeyers.partner_app

import android.app.Application
import ru.anykeyers.partner_app.di.networkModule
import ru.anykeyers.partner_app.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.anykeyers.partner_app.di.repositoryModule

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(networkModule, presentationModule, repositoryModule)
        }
    }

}