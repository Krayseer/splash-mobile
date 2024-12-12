package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentServiceCreateBinding
import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.ui.vm.ServiceViewModel

/**
 * Фрагмент создания/обновления услуги
 */
class ServiceCreateFragment(
    private val isCreate: Boolean,
    private val service: Service? = null,
    private val carWashId: Long = 0
) : Fragment() {

    private val vm: ServiceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceCreateBinding.inflate(inflater, container, false)

        binding.apply {
            submitButton.setOnClickListener {
                val service = Service(0, serviceName.text.toString(), serviceDuration.text.toString().toLong(), servicePrice.text.toString().toInt())
                vm.addService(carWashId, service)
                parentFragmentManager.popBackStack()
            }
            updateButton.setOnClickListener {
                service?.let {
                    val newService = Service(it.id, serviceName.text.toString(), serviceDuration.text.toString().toLong(), servicePrice.text.toString().toInt())
                    vm.updateService(newService)
                    parentFragmentManager.popBackStack()
                }
            }
            deleteButton.setOnClickListener {
                service?.let {
                    vm.deleteService(it.id)
                    parentFragmentManager.popBackStack()
                }
            }
        }

        initByMode(binding)
        initializeIfUpdate(binding)
        return binding.root
    }

    private fun initByMode(binding: FragmentServiceCreateBinding) {
        binding.apply {
            if (isCreate) {
                buttonsBlock.visibility = View.GONE
                submitButton.visibility = View.VISIBLE
                val params = submitButton.layoutParams as LinearLayout.LayoutParams
                submitButton.layoutParams = params
            } else {
                submitButton.visibility = View.GONE
                buttonsBlock.visibility = View.VISIBLE
            }
        }
    }

    private fun initializeIfUpdate(binding: FragmentServiceCreateBinding) {
        binding.apply {
            service?.name?.let { serviceName.setText(it) }
            service?.price?.let { servicePrice.setText(it.toString()) }
            service?.duration?.let { serviceDuration.setText(it.toString()) }
        }
    }

}