package com.hkr.health

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hkr.health.data.Answer
import com.hkr.health.data.AnswersDatabase
import com.hkr.health.data.AnswersRepository
import kotlinx.coroutines.launch
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier

class HealthViewModel(application: Application, private val classifier: BertNLClassifier) : ViewModel() {

    private val answersRepository: AnswersRepository

    private val _answers= MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>>
        get() = _answers

    var answerInput by mutableStateOf("")
        private set

    init {
        val answersDb = AnswersDatabase.getInstance(application)
        val answersDao = answersDb.answersDao()
        answersRepository = AnswersRepository(answersDao)
        viewModelScope.launch {
            _answers.value = answersRepository.getAnswers()
        }
    }

    fun updateAnswerInput(text: String) {
        answerInput = text
    }

    fun insertAnswer(category: String) {
        viewModelScope.launch {
            val (result, score) = classifyUserInput(answerInput)
            val newAnswer = Answer(category, answerInput, result, score)
            answerInput = ""
            answersRepository.insertAnswer(newAnswer)
        }
    }

    fun deleteAnswer(answer: Answer){
        viewModelScope.launch {
            answersRepository.deleteAnswer(answer)
        }
    }

    private fun classifyUserInput(text: String): Pair<String, String> {

        val result = classifier.classify(text)
        val negative = result[0]
        val positive = result[1]

        // Get highest percentage of either positive or negative
        val highest = if(negative.score > positive.score) negative else positive

        // Return neutral if highest score is less than 80% either way
        if(highest.score < 0.8) return "Neutral" to String.format("%.2f", highest.score*100)

        // Return the positive or negative result
        return highest.label to String.format("%.2f", highest.score*100)

    }

    val questions: Map<String, String> = mapOf(
        "alcohol" to "How do your drinking habits affect your day to day life?",
        "nutrition" to "Have you been eating a healthy and balanced diet?",
        "sleep" to "How have you been sleeping recently?",
        "stress" to "How has stress affected you recently?"
    )

    val categories: List<String> = listOf(
        "alcohol",
        "nutrition",
        "sleep",
        "stress"
    )
}

class HealthViewModelFactory(private val application: Application, private val classifier: BertNLClassifier) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HealthViewModel(application, classifier) as T
    }
}