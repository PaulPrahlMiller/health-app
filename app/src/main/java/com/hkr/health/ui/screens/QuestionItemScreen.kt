package com.hkr.health.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkr.health.data.Answer

@Composable
fun QuestionItemScreen(
    category: String,
    answerInput: String,
    answers: List<Answer>,
    questions: Map<String, String>,
    onUserAnswerChange: (String) -> Unit,
    onSubmitClick: (String) -> Unit,
    onBackPress: () -> Unit
) {

    val answer = answers.find { answer -> answer.category == category }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Column (
            Modifier.verticalScroll(state = rememberScrollState())
                ){
            Row {
                Text(
                    text = "QUESTION",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .weight(1f)
                )
            }
            Text(
                text = questions[category]!!,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            OutlinedTextField(
                value = answerInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                onValueChange = onUserAnswerChange,
                label = { Text("Enter your answer") }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onSubmitClick(category) },
                content = {
                    Text(
                        text = "Submit",
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                }
            )
            Text(text = answer?.evaluation ?: "")
            Text(text = answer?.score ?: "")
        }
    }
}
