package ru.anykeyers.partner_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.ui.adapter.BoxAdapter
import ru.anykeyers.partner_app.databinding.FragmentBoxBinding
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.ui.viewmodel.BoxViewModel

/**
 * Вкладка "Боксы" в фрагменте "Услуги и боксы"
 */
class BoxFragment : Fragment() {

    private lateinit var boxAdapter: BoxAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBoxBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[BoxViewModel::class.java]

        boxAdapter = BoxAdapter(emptyList())

        viewModel.boxes.observe(viewLifecycleOwner) {
            boxAdapter.updateData(1)
        }

        binding.apply {
            boxesRecyclerView.layoutManager = LinearLayoutManager(context)
            boxesRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            boxesRecyclerView.adapter = boxAdapter
        }

        return binding.root
    }

}