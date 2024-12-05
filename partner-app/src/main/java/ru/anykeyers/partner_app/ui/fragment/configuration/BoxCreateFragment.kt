package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentBoxCreateBinding
import ru.anykeyers.partner_app.domain.entity.Box

class BoxCreateFragment(
    private val isCreate: Boolean,
    private val box: Box? = null
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBoxCreateBinding.inflate(inflater, container, false)
        initByMode(binding)
        initializeIfUpdate(binding)
        return binding.root
    }

    private fun initByMode(binding: FragmentBoxCreateBinding) {
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

    private fun initializeIfUpdate(binding: FragmentBoxCreateBinding) {
        binding.apply {
            box?.name?.let { serviceName.setText(it) }
        }
    }

}