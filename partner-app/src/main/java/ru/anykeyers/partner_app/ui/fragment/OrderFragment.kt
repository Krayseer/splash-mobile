package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.ui.adapter.OrderAdapter
import ru.anykeyers.partner_app.databinding.FragmentOrdersBinding
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.service.OrderService
import ru.anykeyers.partner_app.service.impl.InMemoryOrderService

class OrderFragment : Fragment() {

    private lateinit var orderAdapter: OrderAdapter

    private var orderService: OrderService = InMemoryOrderService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)
        orderAdapter = OrderAdapter(orderService.loadOrders(1)) { order ->
            val fragment = OrderDetailsFragment()
            val bundle = Bundle().apply {
                putSerializable("order", order)
            }
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.apply {
            ordersRecyclerView.layoutManager = LinearLayoutManager(context)
            ordersRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            ordersRecyclerView.adapter = orderAdapter

        }

        return binding.root
    }

}