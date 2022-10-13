package com.rocket.assessment

import android.app.Application
import com.rocket.assessment.modules.networkModule
import com.rocket.assessment.modules.repositoryModule
import com.rocket.assessment.modules.viewModelModule
import org.koin.core.context.startKoin

class GiantBombApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}