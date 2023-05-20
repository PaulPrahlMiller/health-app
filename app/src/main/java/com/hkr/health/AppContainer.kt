package com.hkr.health

import android.content.Context
import com.hkr.health.data.AnswersDao
import com.hkr.health.data.AnswersDatabase
import com.hkr.health.data.AnswersRepository
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier

interface AppContainer {
    val answersRepository: AnswersRepository
    val classifier: BertNLClassifier
    val answersDao: AnswersDao
    val answersDb: AnswersDatabase
}

class AppContainerImpl(private val applicationContext: Context): AppContainer {
    override val classifier by lazy { MobileBertClassifier(applicationContext).init() }
    override val answersDb by lazy { AnswersDatabase.getInstance(applicationContext) }
    override val answersDao by lazy { answersDb.answersDao() }
    override val answersRepository by lazy { AnswersRepository(answersDao, classifier) }
}