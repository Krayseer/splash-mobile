package ru.anykeyers.partner_app.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.domain.entity.Employee
import ru.anykeyers.partner_app.domain.entity.Role

/**
 * Адаптер для построения таблицы работников
 */
class EmployeeTableAdapter(
    private val context: Context
) {

    /**
     * Заполнить таблицу информацией об услугах
     */
    fun fillTable(tableLayout: TableLayout, employees: List<Employee>) {
        addTableHeader(tableLayout)
        addTableRows(tableLayout, employees)
    }

    private fun addTableHeader(tableLayout: TableLayout) {
        val headerRow = TableRow(context).apply {
            setPadding(0, 0, 0, 0)
        }

        headerRow.addView(createTextView("Имя", isHeader = true))
        headerRow.addView(createTextView("Статус", isHeader = true))
        headerRow.addView(createTextView("Роль", isHeader = true))
        headerRow.addView(createTextView("Почта", isHeader = true))

        tableLayout.addView(headerRow)
    }
    private fun addTableRows(tableLayout: TableLayout, employees: List<Employee>) {
        for (employee in employees) {
            val tableRow = TableRow(context).apply {
                setPadding(0, 0, 0, 0)
            }

            tableRow.addView(createTextView(employee.user.userInfo.firstName, grav = Gravity.START))
            tableRow.addView(createTextView(employee.status.localizeName, grav = Gravity.START))
            tableRow.addView(createTextView(Role.WASHER.localizeName, grav = Gravity.START))
            tableRow.addView(createTextView(employee.user.userInfo.email, grav = Gravity.START))

            tableLayout.addView(tableRow)
        }
    }

    private fun createTextView(text: String,
                               isHeader: Boolean = false,
                               grav: Int = Gravity.CENTER,
                               tface: Int = Typeface.NORMAL): TextView {
        return TextView(context).apply {
            this.text = text
            gravity = grav
            setPadding(32, 32, 32, 32)
            if (isHeader) {
                setTypeface(null, Typeface.BOLD)
                setTextColor(Color.BLACK)
                setBackgroundColor(ContextCompat.getColor(context, R.color.gray_200))
            } else {
                setTypeface(null, tface)
                setTextColor(Color.BLACK)
                setBackgroundResource(R.drawable.cell_border)
            }
        }
    }

}