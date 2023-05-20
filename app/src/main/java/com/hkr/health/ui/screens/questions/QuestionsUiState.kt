package com.hkr.health.ui.screens.questions

import com.hkr.health.data.Answer

data class QuestionsUiState(
    val questions: Map<String, String> = surveyQuestions,
    val categories: List<String> = questionCategories,
    val answers: List<Answer> = emptyList()
)

val questionCategories: List<String> = listOf(
    "alcohol",
    "nutrition",
    "sleep",
    "stress"
)

val surveyQuestions: Map<String, String> = mapOf(
    "alcohol" to "How do your drinking habits affect your day to day life?",
    "nutrition" to "Have you been eating a healthy and balanced diet?",
    "sleep" to "How have you been sleeping recently?",
    "stress" to "How has stress affected you recently?"
)