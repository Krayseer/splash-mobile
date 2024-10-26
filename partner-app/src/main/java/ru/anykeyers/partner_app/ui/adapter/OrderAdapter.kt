package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.ItemOrderBinding
import ru.anykeyers.partner_app.domain.Order
import ru.anykeyers.partner_app.utils.DateUtils

class OrderAdapter(
    private val orders: List<Order>,
    private val onOrderClickListener: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order, onOrderClickListener)
    }

    override fun getItemCount(): Int = orders.size

    private fun getOrderStateStyle(state: Order.State): Int {
        return when (state) {
            Order.State.WAIT_CONFIRM -> R.color.green_200
            Order.State.WAIT_PROCESS -> R.color.green_200
            Order.State.PROCESSING -> R.color.green_200
            Order.State.PROCESSED -> R.color.green_200
        }
    }

    class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(order: Order, onOrderClickListener: (Order) -> Unit) {
            binding.apply {
                orderId.text = "№ ${order.id}"
                orderUser.text = order.user
                orderStatus.text = order.status.localizeName
                orderTime.text = DateUtils.formatMillisToReadableDate(order.time)
                orderBox.text = order.box
                orderPrice.text = "Цена: ${order.price} ₽"
                root.setOnClickListener {
                    onOrderClickListener(order)
                }
            }
        }
    }

}