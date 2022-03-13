package ru.androidlearning.bloodpressurediary.ui.data

data class PressureUiItem(
    val id: String,
    val time: String,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int
) : PressureItemDiff {
    override val itemId: String
        get() = "$TAG$id"
    override val itemHash: Int
        get() = this.hashCode()

    companion object {
        private const val TAG = "PressureUiItem"
    }
}
