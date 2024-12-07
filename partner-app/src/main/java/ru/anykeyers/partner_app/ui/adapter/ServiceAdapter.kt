package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.databinding.ItemServiceBinding
import ru.anykeyers.partner_app.domain.entity.Service
import ru.anykeyers.partner_app.utils.DateUtils

/**
 * Адаптер списка услуг
 */
class ServiceAdapter (
    private var services: List<Service> = mutableListOf(),
    private val onClickService: (Service) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service: Service = services[position]
        holder.bind(service, onClickService)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    fun updateData(services: List<Service>) {
        this.services = services
        notifyDataSetChanged()
    }

    class ServiceViewHolder(
        private val binding: ItemServiceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(service: Service, onClickService: (Service) -> Unit) {
            binding.apply {
                serviceName.text = service.name
                servicePrice.text = "${service.price} ₽"
                serviceDuration.text = "~ ${DateUtils.formatMillisToReadableTime(service.duration)}"

                root.setOnClickListener {
                    onClickService(service)
                }
            }
        }

    }

}