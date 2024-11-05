package ru.anykeyers.partner_app.ui.fragment.order

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentFilterOrdersBinding
import ru.anykeyers.partner_app.domain.entity.Box
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.domain.entity.OrderFilter
import ru.anykeyers.partner_app.ui.vm.OrderFilterViewModel

class OrderFilterFragment : Fragment() {

    private val vm: OrderFilterViewModel by viewModel()

    private val LIST_WITH_EMPTY: List<String> = listOf("Ничего не выбрано")

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilterOrdersBinding.inflate(inflater, container, false)

        setupStatusSpinner(binding)
        setupBoxSpinner(binding)
        setupSaveButton(binding)

        vm.filter.observe(viewLifecycleOwner) { filter -> applyFilter(binding, filter) }

        return binding.root
    }

    private fun setupStatusSpinner(binding: FragmentFilterOrdersBinding) {
        val stateList = LIST_WITH_EMPTY + Order.State.entries.map { it.localizeName }
        val statusAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, stateList)
        statusAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.statusSpinner.adapter = statusAdapter
    }

    private fun setupBoxSpinner(binding: FragmentFilterOrdersBinding) {
        val boxList = LIST_WITH_EMPTY.toMutableList()
        val boxAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, boxList)
        boxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.boxSpinner.adapter = boxAdapter

        vm.boxes.observe(viewLifecycleOwner) { boxes ->
            boxes?.let {
                boxList.clear()
                boxList.addAll(LIST_WITH_EMPTY)
                boxList.addAll(boxes.map { it.name })
                boxAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupSaveButton(binding: FragmentFilterOrdersBinding) {
        binding.saveFilterButton.setOnClickListener {
            val filter = OrderFilter(getSelectedState(binding), getSelectedBox(binding)?.id)
            vm.saveFilter(filter, parentFragmentManager)
        }
    }

    private fun getSelectedState(binding: FragmentFilterOrdersBinding): Order.State? {
        val selectedPosition = binding.statusSpinner.selectedItemPosition
        return if (selectedPosition == 0) null else Order.State.entries[selectedPosition - 1]
    }

    private fun getSelectedBox(binding: FragmentFilterOrdersBinding): Box? {
        val selectedPosition = binding.boxSpinner.selectedItemPosition
        return if (selectedPosition == 0) null else vm.boxes.value?.get(selectedPosition - 1)
    }

    private fun applyFilter(binding: FragmentFilterOrdersBinding, filter: OrderFilter) {
        val statePosition = Order.State.entries.indexOf(filter.selectedOrderState) + 1
        binding.statusSpinner.setSelection(statePosition)

        vm.boxes.observe(viewLifecycleOwner) { boxes ->
            boxes?.let {
                val boxPosition = LIST_WITH_EMPTY.size + boxes.indexOfFirst { it.id == filter.selectedBoxId }
                binding.boxSpinner.setSelection(boxPosition)
            }
        }
    }

}