package ru.anykeyers.client_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.domain.Order

class OrderAdapter(
    private val orders: List<Order>,
    private val onClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var numberAndStatus: TextView = itemView.findViewById(R.id.orderNumberAndStatus)
        val price: TextView = itemView.findViewById(R.id.orderPrice)
        var timeAndDate: TextView = itemView.findViewById(R.id.orderTime)
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
        val status: String = if (order.isDone) "Выполнен" else "В очереди"
        val totalPrice: Int = order.services.sumOf { it.price }
        holder.numberAndStatus.text = "№${order.id} • ${status}"
        holder.price.text = "${totalPrice}₽"
        holder.timeAndDate.text = "${order.date}, ${order.time}"
        holder.itemView.setOnClickListener {
            onClick(order)
        }
    }

}