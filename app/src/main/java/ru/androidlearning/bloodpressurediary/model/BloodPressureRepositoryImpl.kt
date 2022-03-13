package ru.androidlearning.bloodpressurediary.model

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.androidlearning.bloodpressurediary.domain.BloodPressureInfo

class BloodPressureRepositoryImpl : BloodPressureRepository {
    private val db = Firebase.firestore

    override suspend fun getBloodPressureInfoItems(): List<BloodPressureInfo> {
        return db.collection(COLLECTION_NAME)
            .get()
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
            .await()
            .documents.map { document ->
                BloodPressureInfo(
                    id = document.id,
                    systolic = document.data?.get(Keys.SYSTOLIC).toString().toIntOrNull() ?: 0,
                    diastolic = document.data?.get(Keys.DIASTOLIC).toString().toIntOrNull() ?: 0,
                    pulse = document.data?.get(Keys.PULSE).toString().toIntOrNull() ?: 0,
                    dateTime = document.data?.get(Keys.DATE_TIME).toString().toLongOrNull() ?: 0
                )
            }
    }

    override suspend fun saveBloodPressureInfoItem(bloodPressureInfo: BloodPressureInfo) {
        val data = hashMapOf(
            Keys.DATE_TIME to bloodPressureInfo.dateTime,
            Keys.SYSTOLIC to bloodPressureInfo.systolic,
            Keys.DIASTOLIC to bloodPressureInfo.diastolic,
            Keys.PULSE to bloodPressureInfo.pulse
        )
        db.collection(COLLECTION_NAME)
            .add(data)
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
            .await()
    }

    private object Keys {
        const val DATE_TIME = "dateTime"
        const val SYSTOLIC = "systolic"
        const val DIASTOLIC = "diastolic"
        const val PULSE = "pulse"
    }

    companion object {
        private const val TAG = "FirestoreDb"
        private const val COLLECTION_NAME = "Pressure"
    }
}
