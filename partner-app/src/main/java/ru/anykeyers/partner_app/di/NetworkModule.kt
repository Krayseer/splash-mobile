package ru.anykeyers.partner_app.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.anykeyers.partner_app.data.remote.WebConstant
import ru.anykeyers.partner_app.data.remote.ConfigurationAPI
import ru.anykeyers.partner_app.data.remote.OrderAPI
import ru.anykeyers.partner_app.domain.auth.AuthInterceptor
import ru.anykeyers.partner_app.domain.auth.AuthService
import ru.anykeyers.partner_app.domain.auth.TokenProvider

private const val KEYCLOAK_BASE_URL = "http://10.0.2.2:80"
private const val BACKEND_BASE_URL = WebConstant.BASE_URL

private const val TIME_OUT = 6000

val keycloakRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(KEYCLOAK_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val authService: AuthService = keycloakRetrofit.create(AuthService::class.java)

val tokenProvider = TokenProvider(
    authService,
    realm = "splash",
    username = "admin",
    password = "admin",
    clientId = "splash-client",
    clientSecret = "mJlyZ40B2qv81QN8lpek1CWxGlaaW7CK"
)

val client = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor(tokenProvider))
    .build()

val networkModule = module {

    single<Retrofit>(qualifier = named("Keycloak")) { keycloakRetrofit }

    single<Retrofit>(qualifier = named("Backend")) {
        Retrofit.Builder()
            .baseUrl(BACKEND_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { get<Retrofit>(qualifier = named("Keycloak")).create(AuthService::class.java) }
    single { get<Retrofit>(qualifier = named("Backend")).create(ConfigurationAPI::class.java) }
    single { get<Retrofit>(qualifier = named("Backend")).create(OrderAPI::class.java) }

    single {
        HttpClient(Android) {
            install(JsonFeature)
            {
                KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("HttpLogging:", message)
                        }

                    }
                }

                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status:", "${response.status.value}")
                    }
                }

                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }

            }

        }

    }
}
