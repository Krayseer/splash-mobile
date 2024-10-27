package ru.anykeyers.partner_app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.ui.fragment.BoxFragment
import ru.anykeyers.partner_app.ui.fragment.ServiceFragment

class ServiceAndBoxPagerAdapter(
    fragmentActivity: Fragment,
    configuration: MutableLiveData<Configuration>
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        ServiceFragment(configuration),
        BoxFragment(configuration)
    )

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.getOrElse(position) { Fragment() }
    }

}