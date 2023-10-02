package com.example.workmanagerretrofitdemo

import android.app.Application
import androidx.work.Configuration
import com.example.workmanagerretrofitdemo.network.DownloadWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: DownloadWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(workerFactory).build()
    }
}