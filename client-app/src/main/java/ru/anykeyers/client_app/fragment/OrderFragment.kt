package ru.anykeyers.client_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.adapter.OrderAdapter
import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.service.OrderService
import ru.anykeyers.client_app.service.impl.RamOrderService
import java.util.ArrayList

class OrderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    private var orderService: OrderService = RamOrderService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        orderAdapter = OrderAdapter(orderService.loadOrders(0)) { order ->
            openOrderDetailFragment(order)
        }

        recyclerView = view.findViewById(R.id.clientOrderRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = orderAdapter

        return view
    }

    private fun openOrderDetailFragment(order: Order) {
        val fragment = OrderDetailFragment()

        val bundle = Bundle().apply {
            putString("ORDER_TIME", order.time)
            putString("ORDER_DATE", order.date)
            putString("ORDER_ADDRESS", order.address)
            putBoolean("ORDER_IS_DONE", order.isDone)
            putSerializable("ORDER_SERVICES", ArrayList(order.services))
        }
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}