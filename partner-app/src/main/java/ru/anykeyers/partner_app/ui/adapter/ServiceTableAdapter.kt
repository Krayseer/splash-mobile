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
import ru.anykeyers.partner_app.domain.entity.statistics.ServiceStatistics
import ru.anykeyers.partner_app.domain.entity.statistics.SummaryStatistics

/**
 * Адаптер таблицы услуг
 */
class ServiceTableAdapter(
    private val context: Context
) {

    /**
     * Заполнить таблицу информацией об услугах
     */
    fun fillTable(tableLayout: TableLayout, summaryStatistics: SummaryStatistics) {
        addTableHeader(tableLayout)
        addSummaryInfo(tableLayout, summaryStatistics)
        addTableRows(tableLayout, summaryStatistics.services)
    }

    private fun addTableHeader(tableLayout: TableLayout) {
        val headerRow = TableRow(context).apply {
            setPadding(0, 0, 0, 0)
        }

        val header1 = createTextView("Товары", isHeader = true, grav = Gravity.START)
        val header2 = createTextView("Оказано услуг", isHeader = true)
        val header3 = createTextView("Оказано на сумму", isHeader = true)

        headerRow.addView(header1)
        headerRow.addView(header2)
        headerRow.addView(header3)

        tableLayout.addView(headerRow)
    }

    private fun addSummaryInfo(tableLayout: TableLayout, summaryStatistics: SummaryStatistics) {
        val summaryRow = TableRow(context).apply {
            setPadding(0, 0, 0, 0)
        }

        val nameTextView = createTextView("Итого", grav = Gravity.START, tface = Typeface.BOLD)
        val countTextView = createTextView(summaryStatistics.summaryCount.toString(), grav = Gravity.END, tface = Typeface.BOLD)
        val sumTextView = createTextView(summaryStatistics.summaryPrice.toString(), grav = Gravity.END, tface = Typeface.BOLD)

        summaryRow.addView(nameTextView)
        summaryRow.addView(countTextView)
        summaryRow.addView(sumTextView)

        tableLayout.addView(summaryRow)
    }

    private fun addTableRows(tableLayout: TableLayout, data: List<ServiceStatistics>) {
        for (item in data) {
            val tableRow = TableRow(context).apply {
                setPadding(0, 0, 0, 0)
            }

            val nameTextView = createTextView(item.name, grav = Gravity.START)
            val countTextView = createTextView(item.count.toString(), grav = Gravity.END)
            val sumTextView = createTextView(item.sum.toString(), grav = Gravity.END)

            tableRow.addView(nameTextView)
            tableRow.addView(countTextView)
            tableRow.addView(sumTextView)

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
            setPadding(16, 24, 16, 24)
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