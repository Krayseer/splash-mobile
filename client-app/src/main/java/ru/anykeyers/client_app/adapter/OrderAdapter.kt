package ru.anykeyers.client_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.domain.FavoriteOrder
import ru.anykeyers.client_app.domain.Order

class OrderAdapter(
    private var orders: List<Order>,
    private var favoriteOrders: List<FavoriteOrder>,
    private val onClick: (Order) -> Unit,
    private val onFavoriteClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var numberAndStatus: TextView = itemView.findViewById(R.id.orderNumberAndStatus)
        val price: TextView = itemView.findViewById(R.id.orderPrice)
        var timeAndDate: TextView = itemView.findViewById(R.id.orderTime)
        val favoriteButton: ImageView = itemView.findViewById(R.id.favoriteButton)
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
        val isFavorite = favoriteOrders.any { it.id == order.id }

        holder.numberAndStatus.text = "№${order.id} • ${if (order.isDone) "Выполнен" else "В очереди"}"
        holder.price.text = "${order.price}₽"
        holder.timeAndDate.text = "${order.date}, ${order.time}"

        val favoriteTintColor = if (isFavorite) {
            holder.favoriteButton.context.getColor(android.R.color.holo_orange_light)
        } else {
            holder.favoriteButton.context.getColor(android.R.color.darker_gray)
        }
        holder.favoriteButton.setColorFilter(favoriteTintColor)

        holder.itemView.setOnClickListener { onClick(order) }
        holder.favoriteButton.setOnClickListener { onFavoriteClick(order) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()  // Уведомляем адаптер об изменениях
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFavoriteOrders(newFavoriteOrders: List<FavoriteOrder>) {
        favoriteOrders = newFavoriteOrders
        notifyDataSetChanged()
    }

}