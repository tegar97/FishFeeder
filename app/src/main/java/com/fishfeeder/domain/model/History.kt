package com.fishfeeder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val id: Long,
    val title: String,
    val hour: String,
) : Parcelable

