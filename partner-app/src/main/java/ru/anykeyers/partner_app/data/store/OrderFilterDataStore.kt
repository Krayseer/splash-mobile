package ru.anykeyers.partner_app.data.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.firstOrNull
import ru.anykeyers.partner_app.domain.entity.OrderFilter

class OrderFilterDataStore(
    val context: Context
) {

    private val Context.dataStore by preferencesDataStore(name = "order-filter")

    private val gson = Gson()

    /**
     * Получить OrderFilter напрямую, не в виде Flow.
     */
    suspend fun getOrderFilter(): OrderFilter? {
        val preferences = context.dataStore.data.firstOrNull() ?: return null
        val json = preferences[PreferencesKeys.ORDER_FILTER] ?: return null
        return gson.fromJson(json, object : TypeToken<OrderFilter>() {}.type)
    }

    suspend fun saveOrderFilter(orderFilter: OrderFilter) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.ORDER_FILTER] = gson.toJson(orderFilter)
        }
    }

    private object PreferencesKeys {
        val ORDER_FILTER = stringPreferencesKey("order_filter")
    }

}