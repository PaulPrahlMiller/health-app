package com.hkr.health.ui.screens.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hkr.health.data.Answer
import androidx.lifecycle.viewModelScope
import com.hkr.health.data.AnswersRepository
import kotlinx.coroutines.launch


class AnalysisViewModel(
    private val answersRepository: AnswersRepository
): ViewModel() {

    private val _answers= MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>>
        get() = _answers

    companion object {
        fun provideFactory(
            answersRepository: AnswersRepository
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AnalysisViewModel(answersRepository) as T
            }
        }
    }
}

