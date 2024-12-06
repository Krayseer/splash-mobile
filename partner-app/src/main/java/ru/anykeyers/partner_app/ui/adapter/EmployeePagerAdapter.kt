package ru.anykeyers.partner_app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anykeyers.partner_app.ui.fragment.account.company.EmployeeFragment
import ru.anykeyers.partner_app.ui.fragment.account.company.InvitationFragment

class EmployeePagerAdapter(
    fragmentActivity: Fragment
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        EmployeeFragment(),
        InvitationFragment()
    )

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.getOrElse(position) { Fragment() }
    }

}