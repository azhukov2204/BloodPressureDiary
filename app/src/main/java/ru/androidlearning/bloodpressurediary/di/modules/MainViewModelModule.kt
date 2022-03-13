package ru.androidlearning.bloodpressurediary.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidlearning.bloodpressurediary.ui.MainActivity
import ru.androidlearning.bloodpressurediary.ui.MainViewModel

val mainViewModelModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(bloodPressureRepository = get()) }
    }
}