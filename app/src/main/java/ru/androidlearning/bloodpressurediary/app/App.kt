package ru.androidlearning.bloodpressurediary.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.androidlearning.bloodpressurediary.di.diModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(diModules)
        }
    }
}
