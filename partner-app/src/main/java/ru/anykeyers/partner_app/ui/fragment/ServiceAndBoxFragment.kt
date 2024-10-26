package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.anykeyers.partner_app.ui.adapter.ServiceAndBoxPagerAdapter
import ru.anykeyers.partner_app.databinding.FragmentServiceAndBoxBinding

/**
 * Фрагмент "Услуги и боксы"
 */
class ServiceAndBoxFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentServiceAndBoxBinding.inflate(inflater, container, false)

        val adapter = ServiceAndBoxPagerAdapter(this)
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