package ru.anykeyers.partner_app.ui.fragment.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.ui.adapter.BoxAdapter
import ru.anykeyers.partner_app.databinding.FragmentBoxBinding
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration

/**
 * Вкладка "Боксы" в фрагменте "Услуги и боксы"
 */
class BoxFragment(
    private val configuration: MutableLiveData<Configuration>
) : Fragment() {

    private lateinit var boxAdapter: BoxAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBoxBinding.inflate(inflater, container, false)

        boxAdapter = BoxAdapter { box ->
            val fragment = BoxCreateFragment(false, box)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        configuration.observe(viewLifecycleOwner) {
            boxAdapter.updateData(configuration.value?.boxes!!)
        }

        binding.apply {
            addButton.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, BoxCreateFragment(true))
                    ?.addToBackStack(null)
                    ?.commit()
            }

            boxesRecyclerView.layoutManager = LinearLayoutManager(context)
            boxesRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            boxesRecyclerView.adapter = boxAdapter
        }

        return binding.root
    }

}