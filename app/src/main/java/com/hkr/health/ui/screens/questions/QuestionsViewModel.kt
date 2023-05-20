package com.hkr.health.ui.screens.questions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hkr.health.data.AnswersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionsViewModel(private val answersRepository: AnswersRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionsUiState())

    val uiState: StateFlow<QuestionsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            answersRepository.getAnswers()
                .onSuccess { answers -> _uiState.value = _uiState.value.copy(answers = answers) }
        }
    }

    fun submitAnswer(category: String, answer: String) {
        viewModelScope.launch {
            Log.i("Insert method", "Called")
            answersRepository.insertAnswer(category, answer)
            answersRepository.getAnswers()
                .onSuccess { answers ->
                    Log.i("Insert method", answers.toString())
                    _uiState.value = _uiState.value.copy(answers = answers)
                }
        }
    }

    companion object {
        fun provideFactory(
            answersRepository: AnswersRepository
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return QuestionsViewModel(answersRepository) as T
            }
        }
    }
}