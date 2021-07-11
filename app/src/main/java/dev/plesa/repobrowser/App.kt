package dev.plesa.repobrowser

import android.app.Application
import dev.plesa.data.di.remoteModule
import dev.plesa.data.di.repositoryModule
import dev.plesa.domain.di.useCaseModule
import dev.plesa.repobrowser.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModules + domainModules + dataModules)
        }
    }
}

val appModules = listOf(presentationModule)
val domainModules = listOf(useCaseModule)
val dataModules = listOf(remoteModule, repositoryModule)