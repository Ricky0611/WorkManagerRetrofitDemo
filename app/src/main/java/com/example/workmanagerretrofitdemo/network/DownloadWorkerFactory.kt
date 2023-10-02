package com.example.workmanagerretrofitdemo.network

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class DownloadWorkerFactory @Inject constructor(private val apiService: ApiService) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = DownloadWorker(appContext, workerParameters, apiService)
}