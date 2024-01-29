package com.fishfeeder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fishfeeder.data.local.entity.ScheduleEntity

@Dao
interface ScheduleDao {


    @Query("SELECT * FROM schedule ")
    fun getSchedule(): List<ScheduleEntity>

    @Insert
    suspend fun insertSchedule(exercise: ScheduleEntity)


    @Query("UPDATE schedule SET status = :newStatus WHERE id = :scheduleId")
    suspend fun updateScheduleStatus(scheduleId: Long, newStatus: Boolean)
}