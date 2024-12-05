package ru.anykeyers.partner_app.ui.fragment.account.company

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentCompanyBinding
import ru.anykeyers.partner_app.ui.vm.CompanyViewModel

/**
 * Фрагмент информации о компании
 */
class CompanyFragment : Fragment() {

    private val vm: CompanyViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCompanyBinding.inflate(inflater, container, false)

        vm.configuration.observe(viewLifecycleOwner) {
            binding.apply {
                companyName.setText(it.organizationInfo.name)
                openTime.setText(it.openTime)
                closeTime.setText(it.closeTime)
                phoneNumber.setText(it.organizationInfo.phoneNumber)
                description.setText(it.organizationInfo.description)
            }
        }

        binding.submitButton.setOnClickListener {
//            vm.updateConfiguration()
        }

        return binding.root
    }

}