package ru.anykeyers.partner_app.ui.fragment.account.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentInvitationBinding
import ru.anykeyers.partner_app.ui.adapter.InvitationAdapter
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.ui.vm.EmployeeViewModel

/**
 * Фрагмент отправленных приглашений автомойки
 */
class InvitationFragment : Fragment() {

    private val vm: EmployeeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInvitationBinding.inflate(inflater, container, false)
        val adapter = InvitationAdapter()

        vm.invitations.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        binding.invitationRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.invitationRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
        binding.invitationRecyclerView.adapter = adapter

        return binding.root
    }

}