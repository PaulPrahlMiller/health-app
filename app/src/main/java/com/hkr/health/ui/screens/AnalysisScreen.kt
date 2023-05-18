package com.hkr.health.ui.screens

import android.graphics.Color
import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.hkr.health.R
import com.hkr.health.data.Answer

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
            val dataSets: MutableList<IBarDataSet> = mutableListOf()
            val colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW)

            answers.forEachIndexed { index, answer ->
                val dataEntries: MutableList<BarEntry> = mutableListOf()
                dataEntries.add(BarEntry(index.toFloat(), answer.score.toFloat()))
                val dataSet = BarDataSet(dataEntries, answer.category)
                dataSet.color = colors[index]
                dataSets.add(dataSet)
            }
            val barData = BarData(dataSets)
            barData.barWidth = 0.9f
            chart.data = barData
            chart.setFitBars(true)
            chart.background = null
            chart.xAxis
            chart.invalidate()
        }
    )
}