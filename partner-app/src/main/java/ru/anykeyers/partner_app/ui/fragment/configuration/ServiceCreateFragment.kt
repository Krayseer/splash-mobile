package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentServiceCreateBinding
import ru.anykeyers.partner_app.domain.entity.Service

/**
 * Фрагмент создания/обновления услуги
 */
class ServiceCreateFragment(
    private val isCreate: Boolean,
    private val service: Service? = null
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceCreateBinding.inflate(inflater, container, false)
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