package ru.androidlearning.bloodpressurediary.di

import ru.androidlearning.bloodpressurediary.di.modules.bloodPressureRepositoryModule
import ru.androidlearning.bloodpressurediary.di.modules.mainViewModelModule

val diModules = listOf(
    mainViewModelModule,
    bloodPressureRepositoryModule
)
