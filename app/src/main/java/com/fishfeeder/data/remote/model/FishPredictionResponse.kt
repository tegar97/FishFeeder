package com.fishfeeder.data.remote.model

import com.google.gson.annotations.SerializedName

data class FishPredictionResponse(

	@field:SerializedName("confidence")
	val confidence: Double,

	@field:SerializedName("prediction")
	val prediction: String
)
