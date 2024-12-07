package ru.anykeyers.partner_app.ui.vm

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.remote.WebConstant
import ru.anykeyers.partner_app.domain.entity.User
import ru.anykeyers.partner_app.domain.repository.IUserRepository

/**
 * ViewModel для работы с фрагментом пользователя
 */
class AccountViewModel(
    private val userRepository: IUserRepository,
    private val context: Context
) : HandlingViewModel() {

    private val _user by lazy { MutableLiveData<User>() }

    val user: LiveData<User> get() = _user

    init {
        loadUser()
    }

    /**
     * Обновление информации о пользователе
     */
    fun updateUser(user: User) {
        launchWithResultState {
            userRepository.updateUser(user)
        }
    }

    /**
     * Загрузка отчета автомойки в формате PDF
     */
    fun loadReport() {
        val userId = _user.value?.id ?: return
        val reportUrl = "${WebConstant.CAR_WASH_SERVICE_URL}/configuration/pdf/$userId"
        launchWithResultState {
            downloadPdfFile(reportUrl)
        }
    }

    /**
     * Скачивание PDF-файла по указанному URL
     */
    private fun downloadPdfFile(url: String) {
        val request = createDownloadRequest(url)
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
        downloadManager?.enqueue(request)
    }

    /**
     * Создает запрос на загрузку файла
     */
    private fun createDownloadRequest(url: String): DownloadManager.Request {
        return DownloadManager.Request(Uri.parse(url)).apply {
            setTitle("Отчет автомойки")
            setDescription("Идёт загрузка PDF-файла")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.pdf")
            setMimeType("application/pdf")
        }
    }

    /**
     * Загрузка текущего пользователя из репозитория
     */
    private fun loadUser() {
        launchWithResultState {
            _user.value = userRepository.loadCurrentUser()
        }
    }

}