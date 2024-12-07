package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemOrderBinding
import ru.anykeyers.partner_app.domain.entity.Order
import ru.anykeyers.partner_app.utils.DateUtils

/**
 * Адаптер списка заказов
 */
class OrderAdapter(
    private var orders: List<Order> = mutableListOf(),
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

    fun updateData(value: List<Order>) {
        this.orders = value
        notifyDataSetChanged()
    }

    class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(order: Order, onOrderClickListener: (Order) -> Unit) {
            binding.apply {
                orderId.text = "№ ${order.id}"
                orderUser.text = order.user.userInfo.firstName
                orderStatus.text = order.orderState.localizeName
                orderTime.text = DateUtils.formatRangeToReadableTime(order.startTime, order.endTime)
                orderBox.text = order.box.name
                orderPrice.text = "Итого: ${order.services.sumOf { it.price }} ₽"

                root.setOnClickListener {
                    onOrderClickListener(order)
                }
            }
        }
    }

}