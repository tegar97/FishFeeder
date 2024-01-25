package com.fishfeeder.data.remote.retrofit

import com.fishfeeder.data.remote.model.FishPredictionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MlApiService {

    @Multipart
    @POST("/prediction")
    suspend fun predictImage(
        @Part image: MultipartBody.Part,
    ): FishPredictionResponse
}