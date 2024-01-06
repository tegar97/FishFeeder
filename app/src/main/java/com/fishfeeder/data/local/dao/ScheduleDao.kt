package com.fishfeeder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fishfeeder.data.local.entity.ScheduleEntity

@Dao
interface SchenduleDao {


    @Query("SELECT * FROM schedule ")
    fun getSchedule(): List<ScheduleEntity>


    @Insert
    suspend fun insertSchedule(exercise: ScheduleEntity)



}