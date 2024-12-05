package ru.anykeyers.partner_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemBoxBinding
import ru.anykeyers.partner_app.domain.entity.Box

class BoxAdapter(
    private var boxList: List<Box> = mutableListOf(),
    private val onClickBox: (Box) -> Unit
) : RecyclerView.Adapter<BoxAdapter.BoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        val binding = ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val box: Box = boxList[position]
        holder.bind(box, onClickBox)
    }

    override fun getItemCount(): Int {
        return boxList.size
    }

    fun updateData(boxes: List<Box>) {
        boxList = boxes
        notifyDataSetChanged()
    }

    class BoxViewHolder(
        private val binding: ItemBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(box: Box, onClickBox: (Box) -> Unit) {
            binding.apply {
                boxTitle.text = box.name
                root.setOnClickListener {
                    onClickBox(box)
                }
            }
        }

    }

}