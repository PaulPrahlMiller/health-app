package com.hkr.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hkr.health.ui.navigation.HealthNavGraph
import com.hkr.health.ui.screens.questions.QuestionsViewModel
import com.hkr.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as HealthApplication).container
        val viewModel: QuestionsViewModel by viewModels { QuestionsViewModel.provideFactory(appContainer.answersRepository) }


        setContent {
            HealthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HealthNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}