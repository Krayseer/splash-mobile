package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.ui.adapter.ServiceAdapter
import ru.anykeyers.partner_app.databinding.FragmentServiceBinding
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
/**
 * Вкладка "Услуги" в фрагменте "Услуги и боксы"
 */
class ServiceFragment(
    private val configuration: MutableLiveData<Configuration>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceBinding.inflate(inflater, container, false)

        val serviceAdapter = ServiceAdapter()

        configuration.observe(viewLifecycleOwner) { config ->
            config?.let {
                serviceAdapter.updateData(it.services)
            }
        }

        binding.apply {
            serviceRecyclerView.layoutManager = LinearLayoutManager(context)
            serviceRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            serviceRecyclerView.adapter = serviceAdapter
        }

        return binding.root
    }

}