package ru.androidlearning.bloodpressurediary.domain

data class BloodPressureInfo(
    val id: String = "",
    val dateTime: Long,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int
)