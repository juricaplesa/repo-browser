package dev.plesa.repobrowser

import android.app.Application
import dev.plesa.data.di.remoteModule
import dev.plesa.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModules)
        }
    }
}

val dataModules = listOf(remoteModule, repositoryModule)