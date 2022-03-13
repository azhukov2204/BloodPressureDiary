package ru.androidlearning.bloodpressurediary.ui

import kotlinx.coroutines.launch
import ru.androidlearning.bloodpressurediary.core.BaseMVVMViewModel
import ru.androidlearning.bloodpressurediary.domain.BloodPressureInfo
import ru.androidlearning.bloodpressurediary.model.BloodPressureRepository
import ru.androidlearning.bloodpressurediary.ui.data.DateUiItem
import ru.androidlearning.bloodpressurediary.ui.data.PressureItemDiff
import ru.androidlearning.bloodpressurediary.ui.data.PressureUiItem
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val bloodPressureRepository: BloodPressureRepository
) : BaseMVVMViewModel<List<PressureItemDiff>>() {

    fun getData() {
        coroutineScopeIO.launch {
            val items =
                bloodPressureRepository.getBloodPressureInfoItems().sortedBy { it.dateTime }
                    .convertBloodPressureInfoItemsToPressureItemDiff()
            dataLoadingLiveData.postValue(items)
        }
    }

    fun saveData(item: BloodPressureInfo) {
        coroutineScopeIO.launch {
            bloodPressureRepository.saveBloodPressureInfoItem(item)
            getData()
        }
    }

    private fun List<BloodPressureInfo>.convertBloodPressureInfoItemsToPressureItemDiff(): List<PressureItemDiff> {
        val dates: MutableSet<String> = mutableSetOf()
        val resultItems: MutableList<PressureItemDiff> = mutableListOf()
        this.forEach { bloodPressureInfo ->
            dates.add(getDate(bloodPressureInfo.dateTime))
        }
        dates.forEach { date ->
            resultItems.add(DateUiItem(date))
            this.filter { getDate(it.dateTime) == date }.map { bloodPressureInfo ->
                PressureUiItem(
                    id = bloodPressureInfo.id,
                    time = getTime(bloodPressureInfo.dateTime),
                    systolic = bloodPressureInfo.systolic,
                    diastolic = bloodPressureInfo.diastolic,
                    pulse = bloodPressureInfo.pulse
                )
            }.let { pressureItems ->
                resultItems.addAll(pressureItems)
            }
        }
        return resultItems.toList()
    }

    private fun getDate(milliSeconds: Long): String {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = monthMap[calendar.get(Calendar.MONTH)]
        val year = calendar.get(Calendar.YEAR)
        return "$day $month $year"
    }

    private fun getTime(milliSeconds: Long): String {
        val dateFormat: String = TIME_FORMAT
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    companion object {
        private const val TIME_FORMAT = "HH:mm"
        private val monthMap = mapOf(
            0 to "января",
            1 to "февраля",
            2 to "марта",
            3 to "апреля",
            4 to "мая",
            5 to "июня",
            6 to "июля",
            7 to "августа",
            8 to "сетрябра",
            9 to "октября",
            10 to "ноября",
            11 to "декабря",
        )
    }
}
