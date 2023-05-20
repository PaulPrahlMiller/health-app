package com.hkr.health.ui.screens.analysis

import android.graphics.Color
import android.view.LayoutInflater
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.hkr.health.R
import com.hkr.health.data.Answer
import com.hkr.health.ui.components.Heading
import com.hkr.health.ui.screens.questions.QuestionsViewModel
import java.lang.Float.NaN

@Composable
fun Analysis(
    viewModel: QuestionsViewModel
){
    val state = viewModel.uiState.collectAsState()
    Column {
        Heading("Analysis")
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

                state.value.answers.forEachIndexed { index, answer ->
                    val dataEntries: MutableList<BarEntry> = mutableListOf()
                    dataEntries.add(BarEntry(index.toFloat(), answer.score.toFloat()))
                    val dataSet = BarDataSet(dataEntries, "")
                    dataSet.color = answer.setBarColor()
                    dataSets.add(dataSet)
                }

                val barData = BarData(dataSets)
                chart.data = barData
                chart.barData.barWidth = 0.75f
                chart.barData.setDrawValues(false)
                chart.xAxis.valueFormatter = LabelFormatter(state.value.categories.toTypedArray())
                chart.xAxis.setCenterAxisLabels(false)
                chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                chart.xAxis.isGranularityEnabled = true
                chart.xAxis.granularity = 1f
                chart.axisLeft.axisMinimum = 0f
                chart.axisLeft.axisMaximum = 100f
                chart.axisLeft.granularity = 10f
                chart.axisRight.isEnabled = false
                chart.description = null
                chart.setExtraOffsets(5f, 5f, 5f, 20f)
                chart.legend.formToTextSpace = 10f
                chart.legend.setCustom(legendList)
                chart.invalidate()
            },
            modifier = Modifier
                .padding(vertical = 25.dp)
        )
    }
}

class LabelFormatter(val labels: Array<String>): IndexAxisValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return labels[value.toInt()]
    }
}

fun Answer.setBarColor(): Int {
    if (this.evaluation.equals("positive", true)) return Color.GREEN
    if (this.evaluation.equals("negative", true)) return Color.RED
    return Color.YELLOW
}

val legendList = mutableListOf<LegendEntry>(
    LegendEntry("Positive", Legend.LegendForm.CIRCLE, NaN, NaN, null, Color.GREEN),
    LegendEntry("Negative", Legend.LegendForm.CIRCLE, NaN, NaN, null, Color.RED),
    LegendEntry("Neutral", Legend.LegendForm.CIRCLE, NaN, NaN, null, Color.YELLOW)
)