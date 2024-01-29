package com.fishfeeder.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.fishfeeder.data.local.dao.ScheduleDao
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.data.local.util.UiState
import com.fishfeeder.data.remote.model.FishPredictionResponse
import com.fishfeeder.data.remote.retrofit.MlApiService
import com.fishfeeder.ui.screens.adding.AddingEvent
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import retrofit2.HttpException
import javax.inject.Inject


class FishFeederRepository @Inject constructor(private val mlApiService : MlApiService , private val db : ScheduleDao ){


    suspend fun insertSchedule( timeResult : String, nameSchedule: String) {
        val scheduleData = ScheduleEntity(
            title = nameSchedule,
            hour = timeResult
        )
        db.insertSchedule(scheduleData)
    }


    suspend fun updateScheduleStatus(id : Long,status : Boolean){
        db.updateScheduleStatus(id,status)

    }

    fun getAllSchedule() : Flow<List<ScheduleEntity>> {

        return flowOf(db.getSchedule())
    }





    fun predict(image : File)  : Flow<UiState<FishPredictionResponse>> = flow {
        emit(UiState.Loading)
        val requestImageFile = image.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            image.name,
            requestImageFile
        )

        try{
            val predictImageRespnse = mlApiService.predictImage(multipartBody)

            emit(UiState.Success(predictImageRespnse))

        } catch (e: HttpException) {


            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, FishPredictionResponse::class.java)

            errorResponse.status?.message?.let { UiState.Error(it) }?.let { emit(it) }
        }
    }
}