package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.ui.adapter.ServiceAdapter
import ru.anykeyers.partner_app.databinding.FragmentServiceBinding
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.service.ServiceOfServices
import ru.anykeyers.partner_app.service.impl.InMemoryServiceOfServices

/**
 * Вкладка "Услуги" в фрагменте "Услуги и боксы"
 */
class ServiceFragment : Fragment() {

    private lateinit var serviceAdapter: ServiceAdapter

    private var serviceOfServices: ServiceOfServices = InMemoryServiceOfServices()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceBinding.inflate(inflater, container, false)
        serviceAdapter = ServiceAdapter(serviceOfServices.loadServices(1))

        binding.apply {
            serviceRecyclerView.layoutManager = LinearLayoutManager(context)
            serviceRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            serviceRecyclerView.adapter = serviceAdapter
        }

        return binding.root
    }

}