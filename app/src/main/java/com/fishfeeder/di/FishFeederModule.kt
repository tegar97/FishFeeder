package com.fishfeeder.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fishfeeder.data.local.dao.HistoryDao
import com.fishfeeder.data.local.dao.SchenduleDao
import com.fishfeeder.data.local.database.FishFeederDB
import com.fishfeeder.data.remote.model.ApiConstant
import com.fishfeeder.data.remote.retrofit.MainApi
import com.fishfeeder.data.remote.retrofit.MlApi
import com.fishfeeder.data.remote.retrofit.MlApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FishFeederModule {

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


//    @Provides
//    @Singleton
//    @MainApi
//    fun mainApi(
//
//    ): Retrofit {
//        val loggingInterceptor =
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val clientBuilder = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//        val client = clientBuilder.build()
//
//        return  Retrofit.Builder()
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(ApiConstant.EXERCISE_BASE_URL).build()
//    }


    @Provides
    @Singleton
    @MlApi
    fun mlApi(

    )  : Retrofit {

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
        val client = clientBuilder.build()


        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstant.ML_BASE_URL).build()
    }


    // bUILD API SERVICE
//    @Provides
//    @Singleton
//    fun mainApiService(@MainApi retrofit : Retrofit) : ExerciseApiService = retrofit.create(ExerciseApiService::class.java)

    @Provides
    @Singleton
    fun mlApiService(@MlApi retrofit : Retrofit) : MlApiService = retrofit.create(MlApiService::class.java)

    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}