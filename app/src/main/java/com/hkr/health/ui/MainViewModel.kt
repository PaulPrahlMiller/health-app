package com.hkr.health.ui

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hkr.health.data.Answer
import com.hkr.health.data.AnswersRepository
import kotlinx.coroutines.launch
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier

class MainViewModel(private val answersRepository: AnswersRepository, private val classifier: BertNLClassifier) : ViewModel() {

    private val _answers= MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>>
        get() = _answers

    var answerInput by mutableStateOf("")
        private set

    fun updateAnswerInput(text: String) {
        answerInput = text
    }

    fun insertAnswer(category: String, answer: String) {
        viewModelScope.launch {
            val (result, score) = classifyUserInput(answerInput)
            val newAnswer = Answer(category, answer, result, score)
            answerInput = ""
            answersRepository.insertAnswer(category, "newAnswer")
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





    companion object {
        fun provideFactory(
            answersRepository: AnswersRepository,
            classifier: BertNLClassifier
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(answersRepository, classifier) as T
            }
        }
    }
}