package ru.androidlearning.bloodpressurediary.ui.adapter_delegate

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.bloodpressurediary.ui.data.PressureItemDiff

object PressureDiffCallback : DiffUtil.ItemCallback<PressureItemDiff>() {
    override fun areItemsTheSame(oldItem: PressureItemDiff, newItem: PressureItemDiff): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: PressureItemDiff, newItem: PressureItemDiff): Boolean {
        return oldItem.itemHash == newItem.itemHash
    }
}
