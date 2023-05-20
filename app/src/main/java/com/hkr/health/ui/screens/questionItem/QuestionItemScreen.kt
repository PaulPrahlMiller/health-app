package com.hkr.health.ui.screens.questionItem

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkr.health.data.Answer
import com.hkr.health.ui.screens.questions.QuestionsViewModel

@Composable
fun QuestionItemScreen(
    viewModel: QuestionsViewModel,
    category: String,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val item = state.answers.find { it.category == category }
    var answerInput by rememberSaveable { mutableStateOf("") }

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
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier
                        .clickable {
                            onBackClick()
                        }
                )
            }
            if (state.questions[category] != null) {
                Text(
                    text = state.questions[category] ?: "",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
                OutlinedTextField(
                    value = answerInput,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    onValueChange = { answerInput = it },
                    label = { Text("Enter your answer") }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        if (answerInput.isNotEmpty()) {
                            viewModel.submitAnswer(category, answerInput)
                            answerInput = ""
                        }
                    },
                    content = {
                        Text(
                            text = "Submit",
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )
                    }
                )
                if (item != null) {
                    Text(
                        text = "You Answered",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 25.dp)
                    )
                    Text(text = item.answer)
                    Text(
                        text = "Result",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 25.dp)
                    )
                    Text(text = generateResultText(item))
                }
            } else {
                Text("Error")
            }
        }
    }
}

fun generateResultText(answer: Answer): String {
    val categoryString = when(answer.category) {
        "alcohol" ->  "Your habits with alcohol"
        "sleep" -> "Your sleeping habits"
        "stress" -> "Your stress levels"
        "nutrition" -> "Your nutrition"
        else -> ""
    }
    return "$categoryString have had a ${answer.evaluation} impact on you"
}



