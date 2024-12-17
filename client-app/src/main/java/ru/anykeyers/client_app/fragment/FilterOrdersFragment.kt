package ru.anykeyers.client_app.fragment

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Switch
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.anykeyers.client_app.MainActivity
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.cache.FilterOrdersCache

class FilterOrdersFragment : Fragment(R.layout.fragment_filter_orders) {

    private val filterCache: FilterOrdersCache by inject()

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("filters", Context.MODE_PRIVATE)

        val minPriceEditText = view.findViewById<EditText>(R.id.filter_min_price)
        val isDoneSwitch = view.findViewById<Switch>(R.id.filter_is_done)
        val addressEditText = view.findViewById<EditText>(R.id.filter_address)
        val applyButton = view.findViewById<Button>(R.id.apply_filters)

        // Загружаем сохраненные настройки
        minPriceEditText.setText(sharedPreferences.getInt("min_price", 0).toString())
        isDoneSwitch.isChecked = sharedPreferences.getBoolean("is_done", false)
        addressEditText.setText(sharedPreferences.getString("address", ""))

        // Сохраняем фильтры
        applyButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            val minPrice = minPriceEditText.text.toString().toIntOrNull() ?: 0
            val isDone = isDoneSwitch.isChecked
            val address = addressEditText.text.toString()

            // Сохраняем фильтры
            editor.putInt("min_price", minPrice)
            editor.putBoolean("is_done", isDone)
            editor.putString("address", address)
            editor.apply()

            // Обновляем состояние фильтра в FilterCache
            filterCache.hasFilters = minPrice > 0 || isDone || address.isNotEmpty()

            (requireActivity() as MainActivity).updateFilterBadge()

            // Закрываем текущий фрагмент
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
