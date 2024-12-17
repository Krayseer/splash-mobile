package ru.anykeyers.client_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.anykeyers.client_app.di.cacheModule
import ru.anykeyers.client_app.di.networkModule
import ru.anykeyers.client_app.di.repositoryModule
import ru.anykeyers.client_app.di.uiModule

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(networkModule, uiModule, repositoryModule, cacheModule)
        }
    }

}