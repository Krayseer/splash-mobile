package ru.anykeyers.partner_app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anykeyers.partner_app.ui.fragment.BoxFragment
import ru.anykeyers.partner_app.ui.fragment.ServiceFragment

class ServiceAndBoxPagerAdapter(
    fragmentActivity: Fragment
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        ServiceFragment(),
        BoxFragment()
    )

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.getOrElse(position) { Fragment() }
    }

}