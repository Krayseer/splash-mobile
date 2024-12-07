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
import ru.anykeyers.partner_app.domain.entity.Invitation

/**
 * Адаптер для построения таблицы приглашений
 */
class InvitationTableAdapter(
    private val context: Context
) {

    /**
     * Заполнить таблицу информацией об услугах
     */
    fun fillTable(tableLayout: TableLayout, employees: List<Invitation>) {
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
    private fun addTableRows(tableLayout: TableLayout, invitations: List<Invitation>) {
        for (invitation in invitations) {
            val tableRow = TableRow(context).apply {
                setPadding(0, 0, 0, 0)
            }

            tableRow.addView(createTextView(invitation.user.userInfo.firstName, grav = Gravity.START))
            tableRow.addView(createTextView(invitation.invitationState.localizeName, grav = Gravity.START))
            tableRow.addView(createTextView(invitation.roles[0].localizeName, grav = Gravity.START))
            tableRow.addView(createTextView(invitation.user.userInfo.email, grav = Gravity.START))

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