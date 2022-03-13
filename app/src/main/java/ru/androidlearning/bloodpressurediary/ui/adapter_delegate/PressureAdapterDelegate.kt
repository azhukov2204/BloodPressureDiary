package ru.androidlearning.bloodpressurediary.ui.adapter_delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.androidlearning.bloodpressurediary.R
import ru.androidlearning.bloodpressurediary.databinding.ItemPressureBinding
import ru.androidlearning.bloodpressurediary.ui.data.PressureItemDiff
import ru.androidlearning.bloodpressurediary.ui.data.PressureUiItem

fun pressureAdapterDelegate() = adapterDelegateViewBinding<PressureUiItem, PressureItemDiff, ItemPressureBinding>(
    viewBinding = { layoutInflater, root -> ItemPressureBinding.inflate(layoutInflater, root, false) }
) {

    bind {
        with(binding) {
            timeTv.text = item.time
            pressureTv.text = getString(R.string.pressure_format, item.systolic.toString(), item.diastolic.toString())
            pulseTv.text = item.pulse.toString()
        }
    }
}
