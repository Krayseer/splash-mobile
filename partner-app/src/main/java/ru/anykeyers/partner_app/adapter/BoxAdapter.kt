package ru.anykeyers.partner_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.domain.Box

class BoxAdapter(
    private val boxList: List<Box>
) : RecyclerView.Adapter<BoxAdapter.BoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_box, parent, false)
        return BoxViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val box: Box = boxList[position]
        holder.boxTitle.text = box.name
    }

    override fun getItemCount(): Int {
        return boxList.size
    }

    class BoxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var boxTitle: TextView = itemView.findViewById(R.id.boxTitle)
    }

}