package ru.anykeyers.partner_app.ui.fragment.account

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import ru.anykeyers.partner_app.databinding.FragmentAccountEditBinding
import ru.anykeyers.partner_app.domain.entity.User
import java.io.File

class AccountEditFragment(
    private val user: User
) : Fragment() {

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    private var photoUri: Uri? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountEditBinding.inflate(inflater, container, false)

        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val imageUri = result.data?.data ?: photoUri
            imageUri?.let {
                user.userInfo.photoUrl = it.toString()
                binding.avatar.setImageURI(Uri.parse(user.userInfo.photoUrl))
            }
        }

        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedUri = result.data?.data ?: photoUri

                selectedUri?.let {
                    val destinationFile = createImageFile()
                    copyUriToFile(it, destinationFile)
                    photoUri = Uri.fromFile(destinationFile)

                    user.userInfo.photoUrl = photoUri.toString()
                    binding.avatar.setImageURI(photoUri)
                }
            }
        }

        binding.apply {
            firstName.setText(user.userInfo.firstName)
            lastName.setText(user.userInfo.lastName)
            email.setText(user.userInfo.email)
            phone.setText(user.userInfo.phoneNumber)
            user.userInfo.photoUrl?.let {
                avatar.setImageURI(Uri.parse(it))
            }

            avatar.setOnClickListener {
                showImagePickerDialog()
            }

            submitButton.setOnClickListener {
                parentFragmentManager.setFragmentResult("onSave", Bundle.EMPTY)
                parentFragmentManager.popBackStack()
            }
        }

        return binding.root
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Сделать фото", "Выбрать из галереи")

        AlertDialog.Builder(requireContext())
            .setTitle("Выберите действие")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "ru.anykeyers.partner_app.fileprovider",
            photoFile
        )

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        }
        cameraLauncher.launch(cameraIntent)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
        }
        galleryLauncher.launch(galleryIntent)
    }

    private fun createImageFile(): File {
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "photo_${System.currentTimeMillis()}",
            ".jpg",
            storageDir
        )
    }

    private fun copyUriToFile(sourceUri: Uri, destinationFile: File) {
        requireContext().contentResolver.openInputStream(sourceUri).use { inputStream ->
            destinationFile.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
        }
    }

}