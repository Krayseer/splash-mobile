package ru.anykeyers.partner_app.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.domain.Order
import ru.anykeyers.partner_app.utils.DateUtils

class OrderDetailsFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_order_details, container, false)

        val order = arguments?.getSerializable("order") as Order

        view.findViewById<TextView>(R.id.order_detail_id).text = "№ ${order.id}"
        view.findViewById<TextView>(R.id.order_detail_user).text = order.user
        view.findViewById<TextView>(R.id.order_detail_status).text = order.status.localizeName
        view.findViewById<TextView>(R.id.order_detail_time).text = DateUtils.formatMillisToReadableDate(order.time)
        view.findViewById<TextView>(R.id.order_detail_duration).text = "Время выполнения: ${DateUtils.formatMillisToReadableTime(order.services.map{it -> it.duration}.sum())}"
        view.findViewById<TextView>(R.id.order_detail_box).text = order.box
        view.findViewById<TextView>(R.id.order_detail_price).text = "Цена: ${order.price} ₽"
        return view
    }

}