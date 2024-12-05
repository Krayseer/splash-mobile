package ru.anykeyers.partner_app.ui.fragment.account

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.databinding.FragmentAccountBinding
import ru.anykeyers.partner_app.domain.entity.User
import ru.anykeyers.partner_app.ui.fragment.account.company.CompanyFragment
import ru.anykeyers.partner_app.ui.fragment.account.notification.NotificationSettingsFragment
import ru.anykeyers.partner_app.ui.vm.AccountViewModel

class AccountFragment : Fragment() {

    private val vm: AccountViewModel by viewModel()

    private lateinit var user: User

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)

        vm.user.observe(viewLifecycleOwner) {
            user = it
            binding.userNameTextView.text = "${it.userInfo.firstName} ${it.userInfo.lastName}"
            user.userInfo.photoUrl?.let { uri ->
                binding.avatar.setImageURI(Uri.parse(uri))
            }
        }

        binding.editProfileTextView.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, AccountEditFragment(user))
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.notificationSettingsTextView.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, NotificationSettingsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.compInfo.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, CompanyFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.reportButton.setOnClickListener {
            vm.loadReport()
        }

        parentFragmentManager.setFragmentResultListener("onSave", this) {_, _ ->
            vm.updateUser(user)
            user.userInfo.photoUrl?.let {
                binding.avatar.setImageURI(Uri.parse(it))
            }
        }

        return binding.root
    }

}