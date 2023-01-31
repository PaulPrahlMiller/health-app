package com.hkr.health.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnswersDao {

    @Query("SELECT * FROM answers")
    suspend fun getAnswers(): List<Answer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(vararg answer: Answer)

    @Delete
    suspend fun deleteAnswer(vararg answer: Answer)

}