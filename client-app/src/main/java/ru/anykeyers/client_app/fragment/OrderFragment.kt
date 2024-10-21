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
import ru.anykeyers.client_app.service.OrderService
import ru.anykeyers.client_app.service.impl.RamOrderService

class OrderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    private var orderService: OrderService = RamOrderService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        orderAdapter = OrderAdapter(orderService.loadOrders(0))

        recyclerView = view.findViewById(R.id.clientOrderRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = orderAdapter

        return view
    }

}