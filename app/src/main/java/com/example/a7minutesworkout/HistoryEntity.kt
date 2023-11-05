package com.example.a7minutesworkout

import androidx.room.*


@Entity(tableName = "History-TABLE")
data class HistoryEntity(
    @PrimaryKey
    val data :String


)
