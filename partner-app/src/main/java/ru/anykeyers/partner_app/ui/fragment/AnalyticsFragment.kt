package ru.anykeyers.partner_app.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.anykeyers.partner_app.databinding.FragmentAnalyticsBinding
import ru.anykeyers.partner_app.domain.entity.statistics.DateStatistics
import ru.anykeyers.partner_app.ui.adapter.ServiceTableAdapter
import ru.anykeyers.partner_app.ui.vm.StatisticsViewModel

class AnalyticsFragment : Fragment() {

    private lateinit var binding: FragmentAnalyticsBinding

    private val vm: StatisticsViewModel by viewModel()

    private lateinit var serviceTableAdapter: ServiceTableAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false)

        serviceTableAdapter = ServiceTableAdapter(requireContext())

        vm.statistics.observe(viewLifecycleOwner) {
            setupLineChart(binding, it.dateStatistics)
            serviceTableAdapter.fillTable(binding.tableLayout, it.summaryStatistics)
        }

        return binding.root
    }

    private fun setupLineChart(
        binding: FragmentAnalyticsBinding,
        dateStatistics: List<DateStatistics>
    ) {
        val entries = mutableListOf<Entry>()
        val dates = mutableListOf<String>()

        dateStatistics.forEachIndexed { index, orderData ->
            entries.add(Entry(index.toFloat(), orderData.serviceCountSummary.toFloat()))
            dates.add(orderData.date)
        }

        val dataSet = LineDataSet(entries, "Количество услуг")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        binding.lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(dates)
        binding.lineChart.xAxis.setDrawGridLines(false)
        binding.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChart.xAxis.setLabelCount(dates.size, false)
        binding.lineChart.xAxis.granularity = 1f

        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.invalidate()
    }

}