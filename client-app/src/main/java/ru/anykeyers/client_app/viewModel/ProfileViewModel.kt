package ru.anykeyers.client_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.anykeyers.client_app.domain.Profile

class ProfileViewModel : ViewModel() {

    // LiveData для хранения данных профиля
    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile

    init {
        // Загрузка профиля из локального хранилища
        loadProfile()
    }

    private fun loadProfile() {
        val profile = Profile(
            fullName = "Иван Иванов",
            phoneNumber = "79999999090",
            avatarUri = "content://path_to_avatar",
            resumeUrl = "https://example.com/resume.pdf",
            timeNotification = "00:56"
        )
        _profile.value = profile
    }

    fun updateProfile(updatedProfile: Profile) {
        _profile.value = updatedProfile
    }

}