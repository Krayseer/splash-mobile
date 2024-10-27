package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.ui.adapter.OrderAdapter
import ru.anykeyers.partner_app.databinding.FragmentOrdersBinding
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.ui.vm.OrderViewModel

class OrderFragment: Fragment() {

    private lateinit var orderAdapter: OrderAdapter

    private val vm : OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)

        orderAdapter = OrderAdapter(mutableListOf()) { order ->
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

        vm.orders.observe(viewLifecycleOwner) {
            orderAdapter.updateData(vm.orders.value!!)
        }

        binding.apply {
            ordersRecyclerView.layoutManager = LinearLayoutManager(context)
            ordersRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            ordersRecyclerView.adapter = orderAdapter

        }

        return binding.root
    }

}