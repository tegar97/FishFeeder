package com.fishfeeder.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fishfeeder.data.local.dao.HistoryDao
import com.fishfeeder.data.local.dao.SchenduleDao
import com.fishfeeder.data.local.entity.HistoryEntity
import com.fishfeeder.data.local.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class , HistoryEntity::class], version = 1, exportSchema = false)
abstract class FishFeederDB : RoomDatabase() {
    abstract fun schenduleDao() : SchenduleDao
    abstract fun historyDao() : HistoryDao

}