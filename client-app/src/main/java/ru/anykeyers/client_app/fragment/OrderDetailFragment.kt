package ru.anykeyers.client_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.domain.Service

class OrderDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_detail, container, false)

        val servicesContainer = view.findViewById<LinearLayout>(R.id.services_container)

        val backButton: Button = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        val servicesList = (arguments?.getSerializable("ORDER_SERVICES") as? ArrayList<Service>)?.toMutableList()
        val orderTime = arguments?.getString("ORDER_TIME")
        val orderDate = arguments?.getString("ORDER_DATE")
        val orderAddress = arguments?.getString("ORDER_ADDRESS")
        val orderIsDone = arguments?.getBoolean("ORDER_IS_DONE")

        view.findViewById<TextView>(R.id.order_title).text = if (orderIsDone === true) "Заявка выполнена" else "Заявка принята"
        view.findViewById<TextView>(R.id.order_address).text = orderAddress
        view.findViewById<TextView>(R.id.order_date_time).text = "${orderDate}, ${orderTime}"
        view.findViewById<TextView>(R.id.order_total_sum).text = (servicesList?.sumOf { it.price } ?: 0).toString() + " ₽ "
        view.findViewById<TextView>(R.id.order_total_time).text = "~ " + (servicesList?.sumOf { it.duration } ?: 0).toString() + " мин"


        servicesList?.forEach { service ->
            val serviceLayout = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(0, 8, 0, 8)
            }

            val serviceNameTextView = TextView(context).apply {
                text = service.name
                textSize = 16f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val servicePriceTextView = TextView(context).apply {
                text = service.price.toString() + " ₽"
                textSize = 18f
                setTextColor(context.getColor(R.color.black))
                setTypeface(null, android.graphics.Typeface.BOLD)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            serviceLayout.addView(serviceNameTextView)
            serviceLayout.addView(servicePriceTextView)

            servicesContainer.addView(serviceLayout)
        }

        return view
    }

}