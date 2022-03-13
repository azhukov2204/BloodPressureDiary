package ru.androidlearning.bloodpressurediary.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.bloodpressurediary.R
import ru.androidlearning.bloodpressurediary.databinding.DialogEnterPressureBinding
import ru.androidlearning.bloodpressurediary.domain.BloodPressureInfo

class EnterPressureDialog : DialogFragment(R.layout.dialog_enter_pressure) {

    private val viewBinding: DialogEnterPressureBinding by viewBinding(DialogEnterPressureBinding::bind)
    private var onOkClickedListener: ((BloodPressureInfo) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        isCancelable = false
    }

    fun setOnOkClickedListener(listener: (BloodPressureInfo) -> Unit) {
        onOkClickedListener = listener
    }

    private fun initViews() = with(viewBinding) {
        okBtn.setOnClickListener { savePressure() }
    }

    private fun savePressure() = with(viewBinding) {
        val pulse = pulseEt.text.toString().toIntOrNull() ?: 0
        val systolic = systolicEt.text.toString().toIntOrNull() ?: 0
        val diastolic = diastolicEt.text.toString().toIntOrNull() ?: 0

        onOkClickedListener?.invoke(
            BloodPressureInfo(
                dateTime = System.currentTimeMillis(),
                systolic = systolic,
                diastolic = diastolic,
                pulse = pulse
            )
        )
        dismiss()
    }

    companion object {
        fun newInstance() = EnterPressureDialog()
    }
}
