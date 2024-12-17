package ru.anykeyers.client_app.fragment

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.adapter.FavoriteOrderAdapter
import ru.anykeyers.client_app.adapter.OrderAdapter
import ru.anykeyers.client_app.databinding.FragmentFavoritesOrdersBinding
import ru.anykeyers.client_app.databinding.FragmentOrdersBinding
import ru.anykeyers.client_app.domain.FavoriteOrder
import ru.anykeyers.client_app.domain.Order
import ru.anykeyers.client_app.fragment.OrderDetailFragment
import ru.anykeyers.client_app.viewModel.OrderViewModel

class FavoriteOrdersFragment : Fragment() {

    private var _binding: FragmentFavoritesOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: FavoriteOrderAdapter
    private val orderViewModel: OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesOrdersBinding.inflate(inflater, container, false)

        // Инициализация RecyclerView
        orderAdapter = FavoriteOrderAdapter(
            emptyList(),
            onClick = { order -> openOrderDetailFragment(order) }
        )
        binding.favoriteOrdersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoriteOrdersRecyclerView.adapter = orderAdapter

        // Наблюдение за избранными заказами
        orderViewModel.favoriteOrders.observe(viewLifecycleOwner) { favoriteOrders ->
            orderAdapter.updateFavoriteOrders(favoriteOrders)
        }

        return binding.root
    }

    private fun removeFromFavorites(order: Order) {
        orderViewModel.toggleFavorite(order)
    }

    private fun openOrderDetailFragment(order: FavoriteOrder) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}