package ru.androidlearning.bloodpressurediary.ui.adapter_delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.androidlearning.bloodpressurediary.databinding.ItemDateBinding
import ru.androidlearning.bloodpressurediary.ui.data.DateUiItem
import ru.androidlearning.bloodpressurediary.ui.data.PressureItemDiff

fun dateAdapterDelegate() = adapterDelegateViewBinding<DateUiItem, PressureItemDiff, ItemDateBinding>(
    viewBinding = { layoutInflater, root -> ItemDateBinding.inflate(layoutInflater, root, false) }
) {

    bind {
        with(binding) {
            dateTv.text = item.date
        }
    }
}
