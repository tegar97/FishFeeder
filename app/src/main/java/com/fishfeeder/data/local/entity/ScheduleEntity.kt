package com.fishfeeder.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*

    title : untuk nama jadwal yang diset user , semisal Makan siang , Makan pagi
    hour : berfungsi untuk mengambil data jam berapa foreground service di trigger

 */
@Entity(tableName = "schedule")
data class  ScheduleEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,


    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "hour")
    val hour: String,


    @ColumnInfo(name = "status")
    val status: Boolean = false,

)