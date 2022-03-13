package ru.androidlearning.bloodpressurediary.di.modules

import org.koin.dsl.module
import ru.androidlearning.bloodpressurediary.model.BloodPressureRepository
import ru.androidlearning.bloodpressurediary.model.BloodPressureRepositoryImpl

val bloodPressureRepositoryModule = module {
    factory<BloodPressureRepository> { BloodPressureRepositoryImpl() }
}
