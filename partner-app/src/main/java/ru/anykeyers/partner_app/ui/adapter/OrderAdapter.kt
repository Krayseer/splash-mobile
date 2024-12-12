package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
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
                orderPrice.text = "Итого ${order.services.sumOf { it.price }} ₽"

                when (order.orderState) {
                    Order.State.PROCESSING -> {
                        orderStatus.setTextColor(itemView.context.getColor(R.color.white))
                        orderStatusBlock.setCardBackgroundColor(itemView.context.getColor(R.color.lavender_50))
                        orderStatusImage.setImageResource(R.drawable.in_process_order)
                        orderStatusImage.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white), PorterDuff.Mode.SRC_IN)
                    }
                    Order.State.WAIT_CONFIRM -> {
                        orderStatus.setTextColor(itemView.context.getColor(R.color.white))
                        orderStatusBlock.setCardBackgroundColor(itemView.context.getColor(R.color.lavender_50))
                        orderStatusImage.setImageResource(R.drawable.wait_process_order)
                        orderStatusImage.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white), PorterDuff.Mode.SRC_IN)
                    }
                    Order.State.WAIT_PROCESS -> {
                        orderStatus.setTextColor(itemView.context.getColor(R.color.white))
                        orderStatusBlock.setCardBackgroundColor(itemView.context.getColor(R.color.lavender_50))
                        orderStatusImage.setImageResource(R.drawable.queue_process_order)
                        orderStatusImage.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white), PorterDuff.Mode.SRC_IN)
                    }
                    Order.State.PROCESSED -> {
                        orderStatus.setTextColor(itemView.context.getColor(R.color.black))
                        orderStatusBlock.setCardBackgroundColor(itemView.context.getColor(R.color.green_200))
                        orderStatusImage.setImageResource(R.drawable.completed_order)
                    }
                }

                root.setOnClickListener {
                    onOrderClickListener(order)
                }
            }
        }
    }

}