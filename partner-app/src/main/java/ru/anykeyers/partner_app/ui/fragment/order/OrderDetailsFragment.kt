package ru.anykeyers.partner_app.ui.fragment.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.FragmentOrderDetailsBinding
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.ui.adapter.ServiceAdapterSecondary
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
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
            orderUser.text = "${order.user.userInfo.firstName} ${order.user.userInfo.lastName}"
            summaryPrice.text = "${order.services.sumOf { it.price }} ₽"
            summaryTime.text = DateUtils.formatMillisToReadableTime(order.services.sumOf { it.duration })
            orderBox.text = order.box.name
            orderTime.text = DateUtils.formatRangeToReadableTime(order.startTime, order.endTime)

            vm.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    filterIcon.setImageResource(R.drawable.favorite_yes)
                    binding.filterIcon.setOnClickListener { vm.deleteFavorite(order) }
                } else {
                    filterIcon.setImageResource(R.drawable.favorite_no)
                    binding.filterIcon.setOnClickListener { vm.addFavorite(order) }
                }
            }

            serviceRecyclerView.layoutManager = LinearLayoutManager(context)
            serviceRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            serviceRecyclerView.adapter = ServiceAdapterSecondary(order.services)
        }

        return binding.root
    }

}