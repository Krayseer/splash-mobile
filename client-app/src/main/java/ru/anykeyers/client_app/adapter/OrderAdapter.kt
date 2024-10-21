package ru.anykeyers.client_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.domain.Order

class OrderAdapter(
    private val orders: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number: TextView = itemView.findViewById(R.id.orderNumber)
        var price: TextView = itemView.findViewById(R.id.orderCost)
        var time: TextView = itemView.findViewById(R.id.orderTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order: Order = orders[position]
        holder.number.text = order.id.toString()
        holder.price.text = order.price.toString() + "₽"
        holder.time.text = order.time
    }

}