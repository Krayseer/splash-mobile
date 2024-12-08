package ru.anykeyers.partner_app.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.ItemInvitationBinding
import ru.anykeyers.partner_app.domain.entity.Invitation
import ru.anykeyers.partner_app.domain.entity.Role

/**
 * Адаптер списка сотрудников
 */
class InvitationAdapter(
    private var invitations: List<Invitation> = mutableListOf()
) : RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitationViewHolder {
        val binding = ItemInvitationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvitationViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InvitationViewHolder, position: Int) {
        val employee: Invitation = invitations[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return invitations.size
    }

    fun updateData(invitations: List<Invitation>) {
        this.invitations = invitations
        notifyDataSetChanged()
    }

    class InvitationViewHolder(
        private val binding: ItemInvitationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(invitation: Invitation) {
            binding.apply {
                userName.text = "${invitation.user.userInfo.firstName} ${invitation.user.userInfo.lastName}"
                userEmail.text = invitation.user.userInfo.email
                invitationStatus.text = invitation.invitationState.localizeName
                employeeRole.text = invitation.roles[0].localizeName

                when (invitation.invitationState) {
                    Invitation.State.SENT -> {
                        invitationStatus.setTextColor(itemView.context.getColor(R.color.black))
                        invitationStatusCard.setCardBackgroundColor(itemView.context.getColor(R.color.blue_400))
                    }
                    Invitation.State.REJECTED -> {
                        invitationStatus.setTextColor(itemView.context.getColor(R.color.black))
                        invitationStatusCard.setCardBackgroundColor(Color.RED)
                    }
                    Invitation.State.ACCEPTED -> {
                        invitationStatus.setTextColor(itemView.context.getColor(R.color.black))
                        invitationStatusCard.setCardBackgroundColor(itemView.context.getColor(R.color.green_200))
                    }
                }
            }
        }

    }

}