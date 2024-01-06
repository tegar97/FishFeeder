package com.fishfeeder

import android.content.Context
import androidx.room.Room
import com.fishfeeder.data.local.dao.HistoryDao
import com.fishfeeder.data.local.dao.SchenduleDao
import com.fishfeeder.data.local.database.FishFeederDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FishFeederModule {


    @Singleton
    @Provides
    fun getScheduleDao(database: FishFeederDB): SchenduleDao {
        return database.schenduleDao()
    }

    @Singleton
    @Provides
    fun getHistoryDao(database: FishFeederDB): HistoryDao {
        return database.historyDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FishFeederDB {
        return Room.databaseBuilder(
            context.applicationContext, FishFeederDB::class.java, "fishfeeder"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

}