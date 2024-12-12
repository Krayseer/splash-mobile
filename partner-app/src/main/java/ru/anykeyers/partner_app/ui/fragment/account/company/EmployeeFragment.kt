package ru.anykeyers.partner_app.ui.fragment.account.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentEmployeeBinding
import ru.anykeyers.partner_app.ui.adapter.EmployeeAdapter
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.ui.vm.EmployeeViewModel

/**
 * Фрагмент списка работников автомойки
 */
class EmployeeFragment : Fragment() {

    private val vm: EmployeeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        val adapter = EmployeeAdapter()

        vm.employees.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.employeeRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
        binding.employeeRecyclerView.adapter = adapter

        return binding.root
    }

}