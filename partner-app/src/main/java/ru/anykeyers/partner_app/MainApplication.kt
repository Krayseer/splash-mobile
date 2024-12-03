package ru.anykeyers.partner_app

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import ru.anykeyers.partner_app.di.networkModule
import ru.anykeyers.partner_app.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.anykeyers.partner_app.di.contextModule
import ru.anykeyers.partner_app.di.repositoryModule
import ru.anykeyers.partner_app.domain.notification.NotificationConfigurator

class MainApplication: Application() {

    private val notificationConfigurator by lazy { createNotificationConfigurator() }

    override fun onCreate() {
        super.onCreate()
        notificationConfigurator.configureChannel()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(networkModule, presentationModule, repositoryModule, contextModule)
        }
    }

    private fun createNotificationConfigurator(): NotificationConfigurator {
        return NotificationConfigurator(NotificationManagerCompat.from(this), this)
    }

}