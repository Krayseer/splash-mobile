package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemServiceDetailsBinding
import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.utils.DateUtils

/**
 * Адаптер списка услуг
 */
class ServiceAdapterSecondary(
    private var services: List<Service> = mutableListOf()
) : RecyclerView.Adapter<ServiceAdapterSecondary.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service: Service = services[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    fun updateData(services: List<Service>) {
        this.services = services
        notifyDataSetChanged()
    }

    class ServiceViewHolder(
        private val binding: ItemServiceDetailsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(service: Service) {
            binding.apply {
                serviceName.text = service.name
                servicePrice.text = "${service.price} ₽"
                serviceDuration.text = "~ ${DateUtils.formatMillisToReadableTime(service.duration)}"
            }
        }

    }

}