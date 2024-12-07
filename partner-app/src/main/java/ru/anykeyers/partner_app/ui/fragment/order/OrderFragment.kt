package ru.anykeyers.partner_app.ui.fragment.order

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

/**
 * Фрагмент "Заказы"
 */
class OrderFragment: Fragment() {

    private lateinit var orderAdapter: OrderAdapter

    private val vm : OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)

        setupOrders()
        setupFavorite(binding)
        setupFilters(binding)

        binding.apply {
            ordersRecyclerView.layoutManager = LinearLayoutManager(context)
            ordersRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            ordersRecyclerView.adapter = orderAdapter
        }

        return binding.root
    }

    private fun setupOrders() {
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
            it?.let {
                orderAdapter.updateData(it)
            }
        }
    }

    private fun setupFilters(binding: FragmentOrdersBinding) {
        binding.orderFragmentFilter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, OrderFilterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        vm.hasFilter.observe(viewLifecycleOwner) {
            if (it) {
                binding.orderFragmentFilter.setImageResource(R.drawable.filter_selected)
            } else {
                binding.orderFragmentFilter.setImageResource(R.drawable.filter_default)
            }
        }

        parentFragmentManager.setFragmentResultListener("filterApplied", this) { _, _ ->
            vm.toggleFavorite()
        }
    }

    private fun setupFavorite(binding: FragmentOrdersBinding) {
        binding.favoriteIcon.setImageResource(R.drawable.favorite_no)

        vm.isFavorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.favoriteIcon.setImageResource(R.drawable.favorite_yes)
            } else {
                binding.favoriteIcon.setImageResource(R.drawable.favorite_no)
            }

        }
        binding.favoriteIcon.setOnClickListener { vm.toggleFavorite() }
    }

}