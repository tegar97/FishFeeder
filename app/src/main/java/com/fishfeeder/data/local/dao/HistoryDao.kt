package com.fishfeeder.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.fishfeeder.data.local.entity.HistoryEntity
import com.fishfeeder.data.local.entity.ScheduleEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history ")
    fun getHistoryDao(): List<HistoryEntity>
}