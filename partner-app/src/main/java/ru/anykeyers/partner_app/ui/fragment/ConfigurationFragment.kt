package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.ui.adapter.ServiceAndBoxPagerAdapter
import ru.anykeyers.partner_app.databinding.FragmentServiceAndBoxBinding
import ru.anykeyers.partner_app.ui.vm.ConfigurationViewModel

/**
 * Фрагмент "Услуги и боксы"
 */
class ConfigurationFragment : Fragment() {

    private val vm: ConfigurationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceAndBoxBinding.inflate(inflater, container, false)

        val adapter = ServiceAndBoxPagerAdapter(this, vm.configuration)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Услуги"
                1 -> tab.text = "Боксы"
            }
        }.attach()

        return binding.root
    }

}