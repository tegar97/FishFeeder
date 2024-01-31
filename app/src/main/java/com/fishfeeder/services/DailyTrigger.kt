package com.fishfeeder.services

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.fishfeeder.data.local.entity.ScheduleEntity
import com.fishfeeder.data.repository.FishFeederRepository
import com.fishfeeder.utils.Constants.executeThread
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Timer
import javax.inject.Inject

@AndroidEntryPoint
class DailyTrigger : BroadcastReceiver() {

    @Inject
    lateinit var fishFeederRepository: FishFeederRepository

    private val timer = Timer()

    override fun onReceive(context: Context?, intent: Intent?) {
        executeThread {
            val schedule = fishFeederRepository.getListSchedule()

            schedule?.let {
                if (it.isNotEmpty()) {
                    checkAndTriggerAction(context, it)
                } else {
                    Log.d("Schedule", "No eat today")
                }
            }
        }
    }

    private fun checkAndTriggerAction(context: Context?, scheduleList: List<ScheduleEntity>) {
        val currentTimeMillis = System.currentTimeMillis()
        for (schedule in scheduleList) {
            val scheduledTimeMillis = convertTimeToMillis(schedule.hour)
            if (currentTimeMillis >= scheduledTimeMillis && schedule.status) {
                // Perform the action, e.g., send data to MQTT
                sendToMqttService(schedule)
            }
        }
    }

    private fun sendToMqttService(schedule: ScheduleEntity) {
        Log.d("check broadcast" , schedule.title)
    }

    private fun convertTimeToMillis(time: String): Long {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = sdf.parse(time)
        return date?.time ?: 0
    }


}
