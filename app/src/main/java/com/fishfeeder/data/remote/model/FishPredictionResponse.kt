package com.fishfeeder.data.remote.model

import com.google.gson.annotations.SerializedName

data class FishPredictionResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("status")
    val status: Status? = null
)


data class Status(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)
