package ru.androidlearning.bloodpressurediary.ui.data

data class DateUiItem(
    val date: String
) : PressureItemDiff {
    override val itemId: String
        get() = "$TAG$date"
    override val itemHash: Int
        get() = this.hashCode()

    companion object {
        private const val TAG = "DateUiItem"
    }
}
