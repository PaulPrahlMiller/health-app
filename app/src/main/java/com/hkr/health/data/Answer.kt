package com.hkr.health.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "evaluation")
    var evaluation: String,

    @ColumnInfo(name = "score")
    var score: String

)