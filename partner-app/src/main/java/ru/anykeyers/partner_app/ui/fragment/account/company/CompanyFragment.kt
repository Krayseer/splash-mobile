package ru.anykeyers.partner_app.ui.fragment.account.company

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentCompanyBinding
import ru.anykeyers.partner_app.domain.entity.Configuration
import ru.anykeyers.partner_app.domain.entity.OrganizationInfo
import ru.anykeyers.partner_app.domain.entity.dto.ConfigurationUpdateRequest
import ru.anykeyers.partner_app.ui.vm.CompanyViewModel

/**
 * Фрагмент информации о компании
 */
class CompanyFragment : Fragment() {

    private val vm: CompanyViewModel by viewModel()

    private lateinit var config: Configuration

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
            config = it
        }

        binding.reportButton.setOnClickListener {
            vm.loadReport()
        }

        binding.submitButton.setOnClickListener {
            val info = OrganizationInfo(
                config.organizationInfo.tin,
                config.organizationInfo.type,
                config.organizationInfo.email,
                binding.companyName.text.toString(),
                binding.description.text.toString(),
                binding.phoneNumber.text.toString(),
            )
            vm.updateConfiguration(
                ConfigurationUpdateRequest(
                    Gson().toJson(info),
                    binding.openTime.text.toString(),
                    binding.closeTime.text.toString(),
                    config.orderProcessMode,
                    emptyList(),
                    config.address,
                    null
                )
            )
        }

        return binding.root
    }

}