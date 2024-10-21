package ru.anykeyers.partner_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.adapter.OrderAdapter
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.service.OrderService
import ru.anykeyers.partner_app.service.impl.InMemoryOrderService

class OrderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var orderAdapter: OrderAdapter

    private var orderService: OrderService = InMemoryOrderService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
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

        recyclerView = view.findViewById(R.id.orders_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration())
        recyclerView.adapter = orderAdapter

        return view
    }

}