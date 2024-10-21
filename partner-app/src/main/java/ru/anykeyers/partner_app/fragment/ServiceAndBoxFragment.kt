package ru.anykeyers.partner_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.adapter.ServiceAndBoxPagerAdapter

/**
 * Фрагмент "Услуги и боксы"
 */
class ServiceAndBoxFragment : Fragment() {

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_service_and_box, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        val adapter = ServiceAndBoxPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Услуги"
                1 -> tab.text = "Боксы"
            }
        }.attach()

        return view
    }

}