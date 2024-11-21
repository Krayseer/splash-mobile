package ru.anykeyers.partner_app.ui.vm

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.remote.WebConstant
import ru.anykeyers.partner_app.domain.entity.User
import ru.anykeyers.partner_app.domain.repository.IUserRepository

/**
 * VM для работы с фрагментом пользователя
 */
class AccountViewModel(
    private val userRepository: IUserRepository,
    val context: Context
) : HandlingViewModel() {

    private var _user: MutableLiveData<User> = MutableLiveData()

    val user: MutableLiveData<User> get() = _user

    init {
        loadUser()
    }

    fun updateUser(user: User) {
        launchWithResultState  {
            userRepository.updateUser(user)
        }
    }

    fun loadReport() {
        launchWithResultState {
            downloadPdfFile("${WebConstant.CAR_WASH_SERVICE_URL}/configuration/pdf/${_user.value?.id}")
        }
    }

    private fun downloadPdfFile(url: String) {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setTitle("Отчет автомойки")
            setDescription("Идёт загрузка PDF-файла")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.pdf")
            setMimeType("application/pdf")
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun loadUser() {
        launchWithResultState  {
            _user.value = userRepository.loadCurrentUser()
        }
    }

}