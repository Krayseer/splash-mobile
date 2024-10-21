package ru.anykeyers.partner_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.domain.Order
import ru.anykeyers.partner_app.utils.DateUtils

class OrderAdapter(
    private val orders: List<Order>,
    private val onOrderClickListener: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order: Order = orders[position]
        holder.id.text = "№ ${order.id}"
        holder.user.text = order.user
        holder.status.text = order.status.localizeName
        holder.time.text = DateUtils.formatMillisToReadableDate(order.time)
        holder.box.text = order.box
        holder.price.text = "Цена: ${order.price} ₽"

//        holder.statusBlock.setCardBackgroundColor(getOrderStateStyle(order.status))

        holder.itemView.setOnClickListener {
            onOrderClickListener(order)
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    private fun getOrderStateStyle(state: Order.State): Int {
        return when (state) {
            Order.State.WAIT_CONFIRM -> R.color.green_200
            Order.State.WAIT_PROCESS -> R.color.green_200
            Order.State.PROCESSING -> R.color.green_200
            Order.State.PROCESSED -> R.color.green_200
        }
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.order_id)
        var user: TextView = itemView.findViewById(R.id.order_user)
        var status: TextView = itemView.findViewById(R.id.order_status)
        var time: TextView = itemView.findViewById(R.id.order_time)
        var duration: TextView = itemView.findViewById(R.id.order_duration)
        var box: TextView = itemView.findViewById(R.id.order_box)
        var price: TextView = itemView.findViewById(R.id.order_price)

        var statusBlock: CardView = itemView.findViewById(R.id.order_status_block)
    }

}