package com.fishfeeder.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertTimeToMillis(time: String): Long {
    val localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))

    val currentDate = LocalDateTime.now()

    val scheduleDateTime = currentDate.with(localTime)

    return scheduleDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
