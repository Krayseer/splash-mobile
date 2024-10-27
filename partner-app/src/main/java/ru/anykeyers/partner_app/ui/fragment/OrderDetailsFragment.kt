package ru.anykeyers.partner_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentOrderDetailsBinding
import ru.anykeyers.partner_app.domain.entity.Order
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
            orderUser.text = order.user.username
            orderStatus.text = order.orderState.localizeName
            orderTime.text = DateUtils.formatMillisToReadableDate(order.startTime)
            orderDuration.text = "Время выполнения: ${DateUtils.formatMillisToReadableTime(order.endTime - order.startTime)}"
            orderBox.text = order.box.name
            orderPrice.text = "Цена: ${order.services.sumOf { it.price }} ₽"
            orderCreationTimestamp.text = "Время создания заказа: ${order.createdAt}"
        }
        return binding.root
    }

}