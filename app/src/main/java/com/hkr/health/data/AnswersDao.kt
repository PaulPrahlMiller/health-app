package com.hkr.health.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnswersDao {

    @Query("SELECT * FROM answers")
    fun getAnswers(): List<Answer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswer(vararg answer: Answer)

    @Delete
    fun deleteAnswer(vararg answer: Answer)

}