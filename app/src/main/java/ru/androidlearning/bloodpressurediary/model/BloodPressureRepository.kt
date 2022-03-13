package ru.androidlearning.bloodpressurediary.model

import ru.androidlearning.bloodpressurediary.domain.BloodPressureInfo

interface BloodPressureRepository {
    suspend fun getBloodPressureInfoItems(): List<BloodPressureInfo>
    suspend fun saveBloodPressureInfoItem(bloodPressureInfo: BloodPressureInfo)
}