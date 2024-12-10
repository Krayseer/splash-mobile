package ru.anykeyers.partner_app.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.domain.entity.Notification
import ru.anykeyers.partner_app.domain.repository.INotificationRepository

/**
 * ViewModel для работы с уведомлениями
 */
class NotificationViewModel(
    private val notificationRepository: INotificationRepository
) : HandlingViewModel() {

    private val _notifications by lazy { MutableLiveData<List<Notification>>() }

    val notifications: LiveData<List<Notification>> get() = _notifications

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        launchWithResultState {
            _notifications.value = notificationRepository.loadNotifications()
        }
    }

}