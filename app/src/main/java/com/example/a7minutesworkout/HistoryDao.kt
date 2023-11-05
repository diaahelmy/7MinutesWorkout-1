package com.example.a7minutesworkout

import androidx.room.*


import kotlinx.coroutines.flow.Flow


@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("select *from `History-TABLE`")
    fun allfetchqueue(): Flow<List<HistoryEntity>>
}