package ru.anykeyers.partner_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentOrderDetailsBinding
import ru.anykeyers.partner_app.domain.Order
import ru.anykeyers.partner_app.utils.DateUtils

class OrderDetailsFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        val order = arguments?.getSerializable("order") as Order
        binding.apply {
            orderId.text = "№ ${order.id}"
            orderUser.text = order.user
            orderStatus.text = order.status.localizeName
            orderTime.text = DateUtils.formatMillisToReadableDate(order.time)
            orderDuration.text = "Время выполнения: ${DateUtils.formatMillisToReadableTime(order.services.sumOf { it.duration })}"
            orderBox.text = order.box
            orderPrice.text = "Цена: ${order.price} ₽"
        }
        return binding.root
    }

}