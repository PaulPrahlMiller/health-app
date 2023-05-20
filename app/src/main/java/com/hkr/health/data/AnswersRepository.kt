package com.hkr.health.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier

class AnswersRepository(
    private val answersDao: AnswersDao,
    private val classifier: BertNLClassifier,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAnswers(): Result<List<Answer>> {
        return withContext(dispatcher) {
            runCatching {
                answersDao.getAnswers()
            }.onSuccess {
                Result.success(it)
            }.onFailure {
                Result.failure<Throwable>(it)
            }
        }
    }

    suspend fun insertAnswer(category: String, answer: String) {
        withContext(dispatcher) {
            val (result, score) = classifyUserInput(answer)
            val newAnswer = Answer(category, answer, result, score)
            answersDao.insertAnswer(newAnswer)
        }
    }

    suspend fun deleteAnswer(answer: Answer) {
        withContext(dispatcher) {
            answersDao.deleteAnswer(answer)
        }
    }

    private fun classifyUserInput(text: String): Pair<String, String> {

        val result = classifier.classify(text)
        val negative = result[0]
        val positive = result[1]

        // Get highest percentage of either positive or negative
        val highest = if(negative.score > positive.score) negative else positive

        // Return neutral if highest score is less than 80% either way
        if(highest.score < 0.75) return "Neutral" to String.format("%.0f", highest.score*100)

        // Return the positive or negative result
        return highest.label to String.format("%.0f", highest.score*100)

    }

}