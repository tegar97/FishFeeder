package com.fishfeeder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    val id: Long,
    val title: String,
    val hour: String,
    val status : Boolean,
) : Parcelable
