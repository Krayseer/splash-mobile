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

class FavoriteOrderAdapter(
    private var favoriteOrders: List<FavoriteOrder>,
    private val onClick: (FavoriteOrder) -> Unit
) : RecyclerView.Adapter<FavoriteOrderAdapter.FavoriteOrderViewHolder>() {

    // Вью-холдер для избранных заказов
    class FavoriteOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var numberAndStatus: TextView = itemView.findViewById(R.id.orderNumberAndStatus)
        val price: TextView = itemView.findViewById(R.id.orderPrice)
        var timeAndDate: TextView = itemView.findViewById(R.id.orderTime)
    }

    // Создаем вью-холдер для каждого элемента в списке
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteOrderViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return FavoriteOrderViewHolder(view)
    }

    // Возвращаем количество элементов в списке
    override fun getItemCount(): Int {
        return favoriteOrders.size
    }

    // Привязываем данные к каждому элементу
    override fun onBindViewHolder(holder: FavoriteOrderViewHolder, position: Int) {
        val favoriteOrder = favoriteOrders[position]
        val isFavorite = favoriteOrder.isFavorite

        holder.numberAndStatus.text = "№${favoriteOrder.id} • ${if (favoriteOrder.isDone) "Выполнен" else "В очереди"}"
        holder.price.text = "${favoriteOrder.price}₽"
        holder.timeAndDate.text = "${favoriteOrder.date}, ${favoriteOrder.time}"


        // Устанавливаем обработчик клика на заказ
        holder.itemView.setOnClickListener { onClick(favoriteOrder) }
    }

    // Метод для обновления списка избранных заказов
    @SuppressLint("NotifyDataSetChanged")
    fun updateFavoriteOrders(newFavoriteOrders: List<FavoriteOrder>) {
        favoriteOrders = newFavoriteOrders
        notifyDataSetChanged()
    }
}