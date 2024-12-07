package ru.anykeyers.partner_app.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.anykeyers.partner_app.data.remote.WebConstant
import ru.anykeyers.partner_app.data.remote.ConfigurationAPI
import ru.anykeyers.partner_app.data.remote.OrderAPI
import ru.anykeyers.partner_app.data.remote.ServiceAPI
import ru.anykeyers.partner_app.data.remote.StatisticsAPI
import ru.anykeyers.partner_app.data.remote.UserAPI
import ru.anykeyers.partner_app.domain.auth.AuthInterceptor
import ru.anykeyers.partner_app.domain.auth.AuthService
import ru.anykeyers.partner_app.domain.auth.TokenProvider

/**
 * Модуль для работы с сетью
 */
val networkModule = module {

    single<Retrofit>(qualifier = named(ProviderName.KEYCLOAK_PROVIDER)) { keycloakRetrofit }

    single<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)) {
        Retrofit.Builder()
            .baseUrl(WebConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { get<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)).create(UserAPI::class.java) }
    single { get<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)).create(OrderAPI::class.java) }
    single { get<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)).create(ServiceAPI::class.java) }
    single { get<Retrofit>(qualifier = named(ProviderName.KEYCLOAK_PROVIDER)).create(AuthService::class.java) }
    single { get<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)).create(StatisticsAPI::class.java) }
    single { get<Retrofit>(qualifier = named(ProviderName.BACKEND_PROVIDER)).create(ConfigurationAPI::class.java) }

    single<TokenProvider> { tokenProvider }

}

val keycloakRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(WebConstant.KEYCLOAK_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val authService: AuthService = keycloakRetrofit.create(AuthService::class.java)

val tokenProvider = TokenProvider(
    authService,
    realm = "splash",
    username = "krayseer",
    password = "lollallel",
    clientId = "splash-admin",
    clientSecret = "nVswZKJpgVmAisrLQaz7Yt0wLfYaypw3"
)

val client = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor(tokenProvider))
    .build()

/**
 * Названия провайдеров
 */
private object ProviderName {
    const val BACKEND_PROVIDER = "BACKEND"
    const val KEYCLOAK_PROVIDER = "KEYCLOAK"
}