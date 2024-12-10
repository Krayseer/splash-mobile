package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentHomeBinding
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.ui.vm.HomeViewModel

/**
 * Фрагмент "Главная"
 */
class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        vm.ordersCount.observe(viewLifecycleOwner) {
            binding.apply {
                inProcessCount.text = it[Order.State.PROCESSING].toString()
                inQueueCount.text = it[Order.State.WAIT_PROCESS].toString()
                processingCount.text = it[Order.State.WAIT_CONFIRM].toString()
                completedCount.text = it[Order.State.PROCESSED].toString()
            }
        }

        return binding.root
    }

}