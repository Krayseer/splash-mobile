package ru.anykeyers.partner_app.ui.vm

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.anykeyers.partner_app.data.remote.WebConstant
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.dto.ConfigurationUpdateRequest
import ru.anykeyers.partner_app.domain.repository.IConfigurationRepository

/**
 * ViewModel для управления конфигурацией компании
 */
class CompanyViewModel(
    private val configurationRepository: IConfigurationRepository,
    private val context: Context
) : HandlingViewModel() {

    private val _configuration by lazy { MutableLiveData<Configuration>() }

    val configuration: LiveData<Configuration> get() = _configuration

    init {
        loadConfiguration()
    }

    /**
     * Загрузка отчета автомойки в формате PDF
     */
    fun loadReport() {
        val userId = _configuration.value?.userId ?: return
        val reportUrl = "${WebConstant.CAR_WASH_SERVICE_URL}/configuration/pdf/$userId"
        launchWithResultState {
            downloadPdfFile(reportUrl)
        }
    }

    /**
     * Обновляет конфигурацию компании на основе запроса
     */
    fun updateConfiguration(configurationUpdateRequest: ConfigurationUpdateRequest) {
        launchWithResultState {
            configurationRepository.updateConfiguration(configurationUpdateRequest)
        }
    }

    /**
     * Загружает текущую конфигурацию из репозитория
     */
    private fun loadConfiguration() {
        launchWithResultState {
            _configuration.value = configurationRepository.loadConfiguration()
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

}