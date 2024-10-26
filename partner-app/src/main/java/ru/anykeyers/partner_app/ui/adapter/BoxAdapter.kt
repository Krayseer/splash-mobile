package ru.anykeyers.partner_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemBoxBinding
import ru.anykeyers.partner_app.domain.Box
import ru.anykeyers.partner_app.service.BoxService
import ru.anykeyers.partner_app.service.impl.InMemoryBoxService

class BoxAdapter(
    private var boxList: List<Box>
) : RecyclerView.Adapter<BoxAdapter.BoxViewHolder>() {

    private val boxService: BoxService = InMemoryBoxService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        val binding = ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val box: Box = boxList[position]
        holder.bind(box)
    }

    override fun getItemCount(): Int {
        return boxList.size
    }

    fun updateData(carWashId: Long) {
        boxList = boxService.loadBoxes(carWashId)
    }

    class BoxViewHolder(
        private val binding: ItemBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(box: Box) {
            binding.apply {
                boxTitle.text = box.name
            }
        }

    }

}