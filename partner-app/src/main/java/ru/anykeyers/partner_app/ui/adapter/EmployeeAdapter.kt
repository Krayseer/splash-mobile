package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.ItemEmployeeBinding
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Role

/**
 * Адаптер списка сотрудников
 */
class EmployeeAdapter(
    private var employees: List<Employee> = mutableListOf()
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee: Employee = employees[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun updateData(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    class EmployeeViewHolder(
        private val binding: ItemEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(employee: Employee) {
            binding.apply {
                employeeName.text = "${employee.user.userInfo.firstName} ${employee.user.userInfo.lastName}"
                employeeEmail.text = employee.user.userInfo.email
                employeeStatus.text = employee.status.localizeName
                employeeRole.text = Role.WASHER.localizeName

                when (employee.status) {
                    Employee.Status.ACTIVE -> {
                        employeeStatus.setTextColor(itemView.context.getColor(R.color.black))
                        employeeStatusCard.setCardBackgroundColor(itemView.context.getColor(R.color.green_200))
                    }
                    Employee.Status.VACATION, Employee.Status.NON_ACTIVE -> {
                        employeeStatus.setTextColor(itemView.context.getColor(R.color.white))
                        employeeStatusCard.setCardBackgroundColor(itemView.context.getColor(R.color.lavender_50))
                    }
                }
            }
        }

    }

}