package ru.anykeyers.client_app.fragment

import android.Manifest
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout
import ru.anykeyers.client_app.MainActivity
import ru.anykeyers.client_app.R
import ru.anykeyers.client_app.domain.receivers.ReminderReceiver
import java.io.File
import java.util.Calendar

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private lateinit var editAvatarImageView: ShapeableImageView
    private lateinit var editFullName: EditText
    private lateinit var editPhoneNumber: EditText
    private lateinit var saveProfileButton: Button
    private lateinit var timeInput: EditText
    private lateinit var timeInputLayout: TextInputLayout
    private lateinit var timePickerIcon: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    // Запросы для камеры и галереи
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            editAvatarImageView.setImageURI(it)  // Устанавливаем выбранное изображение как аватар
        }
    }

    private val takePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            val photoUri = Uri.fromFile(File(requireContext().cacheDir, "profile_photo.jpg"))
            editAvatarImageView.setImageURI(photoUri)
        }
    }

    // Константа для запроса разрешения на камеру
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE)

        // Получение данных из SharedPreferences (если они есть)
        val fullName = sharedPreferences.getString("fullName", "")
        val phoneNumber = sharedPreferences.getString("phoneNumber", "")
        val avatarPath = sharedPreferences.getString("avatarPath", "")
        val timeNotification = sharedPreferences.getString("timeNotification", "")

        editAvatarImageView = view.findViewById(R.id.editAvatarImageView)
        editFullName = view.findViewById(R.id.editFullName)
        editPhoneNumber = view.findViewById(R.id.editPhoneNumber)
        saveProfileButton = view.findViewById(R.id.saveProfileButton)
        timeInput = view.findViewById(R.id.editFavoriteTime)
        timePickerIcon = view.findViewById(R.id.timePickerIcon)

        editFullName.setText(fullName)
        editPhoneNumber.setText(phoneNumber)
        timeInput.setText(timeNotification)

        // Загрузка изображения (если путь существует)
        if (!avatarPath.isNullOrEmpty()) {
            val file = File(avatarPath)
            if (file.exists()) {
                editAvatarImageView.setImageURI(Uri.fromFile(file))
            }
        }

        timeInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Валидируем поле при каждом изменении
                val isValid = validateTimeField()
                saveProfileButton.isEnabled = isValid // Включаем/выключаем кнопку
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        timePickerIcon.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                timeInput.setText(formattedTime)
            }, hour, minute, true)

            timePicker.show()
        }

        // Обработчик нажатия на аватар
        editAvatarImageView.setOnClickListener {
            showImageSourceDialog()
        }

        // Обработчик нажатия на кнопку сохранения
        saveProfileButton.setOnClickListener {
            val updatedFullName = editFullName.text.toString()
            val updatedPhoneNumber = editPhoneNumber.text.toString()
            val updatedTimeNotification = timeInput.text.toString()

            // Сохраняем обновленные данные в SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("fullName", updatedFullName)
            editor.putString("phoneNumber", updatedPhoneNumber)
            editor.putString("timeNotification", updatedTimeNotification)

            // Сохраняем путь к изображению
            val avatarFile = File(requireContext().cacheDir, "profile_photo.jpg")
            if (avatarFile.exists()) {
                editor.putString("avatarPath", avatarFile.absolutePath)
            }

            editor.apply()
            scheduleNotification(updatedTimeNotification, updatedFullName)
            activity?.onBackPressed()
        }
    }

    fun checkNotificationPermissions(context: Context): Boolean {
        // Check if notification permissions are granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val isEnabled = notificationManager.areNotificationsEnabled()

            if (!isEnabled) {
                // Open the app notification settings if notifications are not enabled
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                context.startActivity(intent)

                return false
            }
        } else {
            val areEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()

            if (!areEnabled) {
                // Open the app notification settings if notifications are not enabled
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                context.startActivity(intent)

                return false
            }
        }
        return true
    }

    private fun validateTimeField(): Boolean {
        val time = timeInput.text.toString()
        return if (time.matches(Regex("^([01]\\d|2[0-3]):([0-5]\\d)$"))) {
            timeInput.error = null  // Убираем ошибку
            true
        } else {
            timeInput.error = "Введите время в формате HH:mm"  // Показываем ошибку
            false
        }
    }

    private fun scheduleNotification(time: String, userName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1)
                return
            }
        }

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Проверяем, разрешено ли устанавливать точные будильники
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                // Уведомляем пользователя, что разрешение не предоставлено
                Toast.makeText(requireContext(), "Разрешение на установку точных будильников не предоставлено.", Toast.LENGTH_SHORT).show()

                // Направляем пользователя в настройки, чтобы предоставить разрешение
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
                return
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "reminder_channel"
            val channelName = "Reminder Notifications"
            val channelDescription = "Channel for reminder notifications"

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            // Получаем NotificationManager и создаем канал
            val notificationManager = requireContext().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(requireContext(), ReminderReceiver::class.java).apply {
            putExtra("userName", userName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Разбираем время в формате HH:mm
        val timeParts = time.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        // Устанавливаем время для напоминания
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        // Устанавливаем точный будильник
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        // Создаем PendingIntent для открытия приложения при нажатии на уведомление
        val openAppIntent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val openAppPendingIntent = PendingIntent.getActivity(requireContext(), 0, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Создаем уведомление
        val notificationBuilder = NotificationCompat.Builder(requireContext(), "reminder_channel")
            .setSmallIcon(R.drawable.dots_vertical)
            .setContentTitle("Напоминание")
            .setContentText("Напоминание для $userName в $time")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(openAppPendingIntent) // При нажатии открываем приложение

        val notificationManagerCompat = NotificationManagerCompat.from(requireContext())
        notificationManagerCompat.notify(1, notificationBuilder.build())
    }


    // Показываем диалог выбора источника изображения (камера или галерея)
    private fun showImageSourceDialog() {
        val options = arrayOf("Выбрать из галереи", "Сделать фото")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите источник изображения")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> pickImageFromGallery() // Открыть галерею
                1 -> openCamera()           // Сделать фото
            }
        }
        builder.show()
    }

    // Открытие галереи для выбора изображения
    private fun pickImageFromGallery() {
        pickImageLauncher.launch("image/*")
    }

    // Открытие камеры для фото
    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    private fun takePhoto() {
        val photoFile = File(requireContext().cacheDir, "profile_photo.jpg")

        val photoUri = FileProvider.getUriForFile(
            requireContext(),
            "ru.anykeyers.client_app.fileprovider",
            photoFile
        )

        // Запускаем камеру с URI
        takePhotoLauncher.launch(photoUri)
    }

    // Обработка результатов запроса разрешений
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()  // Разрешение получено, откроем камеру
                } else {

                }
            }
        }
    }
}
