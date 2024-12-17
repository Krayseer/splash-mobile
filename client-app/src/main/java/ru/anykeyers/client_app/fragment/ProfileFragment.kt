package ru.anykeyers.client_app.fragment

import android.Manifest
import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.viewModel.ProfileViewModel
import java.io.File

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val STORAGE_PERMISSION_REQUEST_CODE = 1002
    private val WRITE_STORAGE_PERMISSION_REQUEST_CODE = 1003

    // Запуск запроса разрешений для хранилища
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Разрешение получено
            navigateToEditProfileScreen()
        } else {
            // Разрешение отклонено
            Toast.makeText(requireContext(), "Разрешение отклонено", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)
        val fullNameTextView: TextView = view.findViewById(R.id.fullNameTextView)
        val phoneTextView: TextView = view.findViewById(R.id.phoneTextView)
        val editButton: Button = view.findViewById(R.id.editProfileButton)
        val resumeButton: Button = view.findViewById(R.id.openResumeButton)


        // Получаем SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE)
        val fullName = sharedPreferences.getString("fullName", "Имя пока что не задано")
        val phoneNumber = sharedPreferences.getString("phoneNumber", "Номер телефона пока что не задан")
        val avatarPath = sharedPreferences.getString("avatarPath", null)

        fullNameTextView.text = fullName
        phoneTextView.text = phoneNumber

        avatarPath?.let {
            val avatarFile = File(it)
            if (avatarFile.exists()) {
                avatarImageView.setImageURI(Uri.fromFile(avatarFile))
            }
        }

        editButton.setOnClickListener {
            navigateToEditProfileScreen()
        }

        resumeButton.setOnClickListener {
            val url = "https://drive.google.com/uc?export=download&id=1Fea-J7F3pViwSEp2g4EL-QRfUyK5to3n\n"  // URL вашего файла (ссылка на резюме)

            downloadFile(url)
        }
    }

    private fun downloadFile(url: String) {
        // Создаем запрос на загрузку
        val request = DownloadManager.Request(Uri.parse(url))
            .setMimeType("application/pdf")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setTitle("Resume") // Название файла в уведомлении
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // Показывать уведомление о загрузке
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloaded_file.pdf") // Путь сохранения файла

        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        // Добавляем запрос в очередь на загрузку
        downloadManager.enqueue(request)
    }


    // Проверка разрешения на доступ к хранилищу
    private fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Разрешение получено
            navigateToEditProfileScreen()
        } else {
            // Запросить разрешение
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_REQUEST_CODE)
        }
    }

    private fun navigateToEditProfileScreen() {
        val editProfileFragment = EditProfileFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, editProfileFragment)
            .addToBackStack(null)
            .commit()
    }

}