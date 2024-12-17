package ru.anykeyers.client_app.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.adapter.OrderAdapter
import ru.anykeyers.client_app.databinding.FragmentOrdersBinding
import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.fragment.OrderDetailFragment
import ru.anykeyers.client_app.viewModel.OrderViewModel

class OrderFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: OrderAdapter
    private val orderViewModel: OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        // Инициализируем RecyclerView и LayoutManager
        orderAdapter = OrderAdapter(
            emptyList(),
            emptyList(),
            onClick = { order -> openOrderDetailFragment(order) },
            onFavoriteClick = { order -> toggleFavorite(order) }
        )
        binding.clientOrderRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.clientOrderRecyclerView.adapter = orderAdapter

        // Наблюдаем за состоянием загрузки
        orderViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Наблюдаем за ошибками
        orderViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) {
                Log.e("OrderFragment", "Ошибка: $errorMessage")
                binding.errorTextView.text = errorMessage
                binding.errorTextView.visibility = View.VISIBLE
                orderAdapter.updateOrders(emptyList()) // сбрасываем список
            } else {
                binding.errorTextView.visibility = View.GONE
            }
        })

        // Наблюдаем за списком заказов и применяем фильтры
        orderViewModel.orders.observe(viewLifecycleOwner, Observer { orders ->
            val filteredOrders = applyFilters(orders)
            orderAdapter.updateOrders(filteredOrders)
        })

        orderViewModel.favoriteOrders.observe(viewLifecycleOwner, Observer { favoriteOrders ->
            orderAdapter.updateFavoriteOrders(favoriteOrders)
        })

        return binding.root
    }

    private fun toggleFavorite(order: Order) {
        orderViewModel.toggleFavorite(order)
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

    private fun applyFilters(orders: List<Order>): List<Order> {
        val sharedPreferences = requireContext().getSharedPreferences("filters", Context.MODE_PRIVATE)

        val minPrice = sharedPreferences.getInt("min_price", 0)
        val isDone = sharedPreferences.getBoolean("is_done", false)
        val address = sharedPreferences.getString("address", "") ?: ""

        return orders.filter {
            val matchesPrice = (it.services.sumOf { it.price } ?: 0) >= minPrice

            val matchesDone = if (isDone) it.isDone == true else true

            val matchesAddress = address.isEmpty() || (it.address?.contains(address, ignoreCase = true) == true)

            matchesPrice && matchesDone && matchesAddress
        }
    }

    override fun onResume() {
        super.onResume()
        orderViewModel.orders.value?.let { orders ->
            val filteredOrders = applyFilters(orders)
            orderAdapter.updateOrders(filteredOrders)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
