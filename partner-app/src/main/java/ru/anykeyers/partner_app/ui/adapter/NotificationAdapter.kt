package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemNotificationBinding
import ru.anykeyers.partner_app.domain.entity.Notification
import ru.anykeyers.partner_app.utils.DateUtils

/**
 * Адаптер уведомлений
 */
class NotificationAdapter(
    private var notifications: List<Notification> = mutableListOf(),
    private val onDeleteNotification: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification: Notification = notifications[position]
        holder.bind(notification, onDeleteNotification)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun updateData(notifications: List<Notification>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }

    class NotificationViewHolder(
        private val binding: ItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(notification: Notification, onDeleteNotification: (Notification) -> Unit) {
            binding.apply {
                notificationName.text = notification.subject
                notificationText.text = notification.message
                notificationTime.text = DateUtils.formatIsoToReadableDate(notification.createdAt)

                notificationDeleteButton.setOnClickListener {
                    onDeleteNotification(notification)
                }
            }
        }
    }

}