package ru.anykeyers.partner_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.adapter.BoxAdapter
import ru.anykeyers.partner_app.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.service.BoxService
import ru.anykeyers.partner_app.service.impl.InMemoryBoxService

/**
 * Вкладка "Боксы" в фрагменте "Услуги и боксы"
 */
class BoxFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var boxAdapter: BoxAdapter

    private var boxService: BoxService = InMemoryBoxService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_box, container, false)
        boxAdapter = BoxAdapter(boxService.loadBoxes(1))

        recyclerView = view.findViewById(R.id.boxes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration())
        recyclerView.adapter = boxAdapter

        return view
    }

}