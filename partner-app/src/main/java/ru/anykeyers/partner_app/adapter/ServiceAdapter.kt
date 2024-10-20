package ru.anykeyers.partner_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.domain.Service
import ru.anykeyers.partner_app.utils.DateUtils

class ServiceAdapter (
    private val services: List<Service>
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service: Service = services[position]
        holder.name.text = service.name
        holder.price.text = service.price.toString() + "₽"
        holder.duration.text = "~" + DateUtils.formatMillisToReadableTime(service.duration)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.service_name)
        var price: TextView = itemView.findViewById(R.id.service_price)
        var duration: TextView = itemView.findViewById(R.id.service_duration)
    }

}