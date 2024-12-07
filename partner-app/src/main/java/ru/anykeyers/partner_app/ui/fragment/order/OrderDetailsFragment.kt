package ru.anykeyers.partner_app.ui.fragment.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.FragmentOrderDetailsBinding
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.ui.vm.OrderDetailsViewModel
import ru.anykeyers.partner_app.utils.DateUtils

/**
 * Фрагмент "Детали заказа"
 */
class OrderDetailsFragment : Fragment() {

    private lateinit var vm: OrderDetailsViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        val order = arguments?.getSerializable("order") as Order

        vm = getViewModel {
            parametersOf(order.id)
        }

        binding.apply {
            orderId.text = "№ ${order.id}"
            orderUser.text = order.user.username
            orderStatus.text = order.orderState.localizeName
            orderTime.text = DateUtils.formatMillisToReadableDate(order.startTime)
            orderDuration.text = "Время выполнения: ${DateUtils.formatMillisToReadableTime(order.endTime - order.startTime)}"
            orderBox.text = order.box.name
            orderPrice.text = "Цена: ${order.services.sumOf { it.price }} ₽"
            orderCreationTimestamp.text = "Время создания заказа: ${order.createdAt}"

            vm.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    filterIcon.setImageResource(R.drawable.favorite_yes)
                    binding.filterIcon.setOnClickListener { vm.deleteFavorite(order) }
                } else {
                    filterIcon.setImageResource(R.drawable.favorite_no)
                    binding.filterIcon.setOnClickListener { vm.addFavorite(order) }
                }
            }
        }

        return binding.root
    }

}