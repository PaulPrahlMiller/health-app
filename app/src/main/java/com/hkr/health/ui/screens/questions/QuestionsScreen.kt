package com.hkr.health.ui.screens.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hkr.health.ui.components.Heading
import com.hkr.health.ui.screens.questions.composables.CardContent

@Composable
fun Questions(
    viewModel: QuestionsViewModel,
    onQuestionClick: (String) -> Unit,
){
    val state by viewModel.uiState.collectAsState()
    Column() {
        Heading("Categories")
        LazyColumn() {
            items(state.categories){ category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .clickable(
                            enabled = true,
                            onClick = { onQuestionClick(category) }
                        ),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp
                ) {
                    CardContent(category)
                }
            }
        }
    }
}