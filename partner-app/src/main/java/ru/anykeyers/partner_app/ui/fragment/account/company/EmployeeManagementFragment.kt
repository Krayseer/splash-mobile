package ru.anykeyers.partner_app.ui.fragment.account.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.anykeyers.partner_app.databinding.FragmentEmployeeManagementBinding
import ru.anykeyers.partner_app.ui.adapter.EmployeePagerAdapter

class EmployeeManagementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeManagementBinding.inflate(inflater, container, false)

        val adapter = EmployeePagerAdapter(this)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Список"
                1 -> tab.text = "История приглашений"
            }
        }.attach()

        return binding.root
    }

}