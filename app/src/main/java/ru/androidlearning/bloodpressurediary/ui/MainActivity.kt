package ru.androidlearning.bloodpressurediary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import ru.androidlearning.bloodpressurediary.R
import ru.androidlearning.bloodpressurediary.databinding.ActivityMainBinding
import ru.androidlearning.bloodpressurediary.ui.adapter_delegate.PressureDiffCallback
import ru.androidlearning.bloodpressurediary.ui.adapter_delegate.dateAdapterDelegate
import ru.androidlearning.bloodpressurediary.ui.adapter_delegate.pressureAdapterDelegate
import ru.androidlearning.bloodpressurediary.ui.data.PressureItemDiff
import ru.androidlearning.bloodpressurediary.ui.dialog.EnterPressureDialog

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    private val viewModel: MainViewModel by viewModel()
    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val mainAdapter by lazy {
        AsyncListDifferDelegationAdapter(
            PressureDiffCallback,
            pressureAdapterDelegate(),
            dateAdapterDelegate()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeToLiveData()
    }

    private fun initViews() = with(viewBinding) {
        mainRv.adapter = mainAdapter
        mainFab.setOnClickListener {
            openEnterPressureDialog()
        }
    }

    private fun observeToLiveData() {
        viewModel.getLiveData().observe(this) { items ->
            render(items)
        }
        viewModel.getData()
    }

    private fun render(items: List<PressureItemDiff>) {
        mainAdapter.items = items
    }

    private fun openEnterPressureDialog() {
        val enterPressureDialog: EnterPressureDialog = EnterPressureDialog.newInstance()
        enterPressureDialog.setOnOkClickedListener(viewModel::saveData)
        enterPressureDialog.show(supportFragmentManager, DIALOG_TAG)
    }

    companion object {
        private const val DIALOG_TAG = "EnterPressureDialog"
    }
}
