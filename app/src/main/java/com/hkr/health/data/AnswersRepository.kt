package com.hkr.health.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswersRepository(private val answersDao: AnswersDao) {

    suspend fun getAnswers(): List<Answer> {
        return answersDao.getAnswers()
    }

    suspend fun insertAnswer(newAnswer: Answer) {
        answersDao.insertAnswer(newAnswer)
    }

    suspend fun deleteAnswer(answer: Answer) {
        answersDao.deleteAnswer(answer)
    }

}