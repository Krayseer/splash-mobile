package ru.anykeyers.partner_app.ui.fragment.account.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentNotificationBinding
import ru.anykeyers.partner_app.ui.adapter.NotificationAdapter
import ru.anykeyers.partner_app.ui.decorator.VerticalSpaceItemDecoration
import ru.anykeyers.partner_app.ui.vm.NotificationViewModel

/**
 * Фрагмент "Уведомления"
 */
class NotificationFragment : Fragment() {

    private val vm: NotificationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotificationBinding.inflate(inflater, container, false)

        val adapter = NotificationAdapter { notification ->
            vm.deleteNotification(notification)
        }
        vm.notifications.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        binding.apply {
            notificationsRecyclerView.layoutManager = LinearLayoutManager(context)
            notificationsRecyclerView.addItemDecoration(VerticalSpaceItemDecoration())
            notificationsRecyclerView.adapter = adapter
        }

        return binding.root
    }

}