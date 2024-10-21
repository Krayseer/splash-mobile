package ru.anykeyers.partner_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.adapter.ServiceAdapter
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.service.ServiceOfServices
import ru.anykeyers.partner_app.service.impl.InMemoryServiceOfServices

/**
 * Вкладка "Услуги" в фрагменте "Услуги и боксы"
 */
class ServiceFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter

    private var serviceOfServices: ServiceOfServices = InMemoryServiceOfServices()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_service, container, false)
        serviceAdapter = ServiceAdapter(serviceOfServices.loadServices(1))

        recyclerView = view.findViewById(R.id.service_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration())
        recyclerView.adapter = serviceAdapter

        return view
    }

}