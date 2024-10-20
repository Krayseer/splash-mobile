package ru.anykeyers.partner_app.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anykeyers.partner_app.fragment.BoxFragment
import ru.anykeyers.partner_app.fragment.ServiceFragment

class ServiceAndBoxPagerAdapter(
    fragmentActivity: Fragment
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ServiceFragment()
            1 -> BoxFragment()
            else -> Fragment()
        }
    }

}