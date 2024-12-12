package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.R
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

    private var carWashId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceBinding.inflate(inflater, container, false)

        val serviceAdapter = ServiceAdapter { service ->
            val fragment = ServiceCreateFragment(false, service = service)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        configuration.observe(viewLifecycleOwner) { config ->
            config?.let {
                serviceAdapter.updateData(it.services)
                carWashId = config.id
            }
        }

        binding.apply {
            addButton.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, ServiceCreateFragment(true, carWashId = carWashId))
                    ?.addToBackStack(null)
                    ?.commit()
            }

            serviceRecyclerView.layoutManager = LinearLayoutManager(context)
            serviceRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            serviceRecyclerView.adapter = serviceAdapter
        }

        return binding.root
    }

}