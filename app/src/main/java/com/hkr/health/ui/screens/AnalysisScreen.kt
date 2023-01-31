package com.hkr.health.ui.screens

import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.hkr.health.R
import com.hkr.health.data.Answer
import java.lang.reflect.Modifier

@Composable
fun Analysis(
    answers: State<List<Answer>>
){
 BarChart(answers = answers.value)
}

@Composable
fun BarChart(
    answers: List<Answer>
) {
    println(answers.toString())
    AndroidView(
        factory = {context ->
            val view = LayoutInflater
                .from(context)
                .inflate(R.layout.bar_chart, null, false)
             view
        },
        update = { view ->
            val chart = view.findViewById<BarChart>(R.id.chart)
            val dataEntries: MutableList<BarEntry> = mutableListOf()

            dataEntries.add(BarEntry(5.0f, 10.0f))
            dataEntries.add(BarEntry(3.0f, 7.0f))
            val dataset: BarDataSet = BarDataSet(dataEntries, "Label")
            val barData: BarData = BarData(dataset)
            barData.barWidth = 0.9f
            chart.data = barData
            chart.setFitBars(true)
            chart.background = null
            chart.xAxis
            chart.invalidate()
        }
    )
}