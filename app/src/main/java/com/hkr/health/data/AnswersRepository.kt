package com.hkr.health.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswersRepository(private val answersDao: AnswersDao) {

    var answers: List<Answer> = answersDao.getAnswers()

    suspend fun insertAnswer(newAnswer: Answer) {
        answersDao.insertAnswer(newAnswer)
    }

    suspend fun deleteAnswer(answer: Answer) {
        answersDao.deleteAnswer(answer)
    }

}