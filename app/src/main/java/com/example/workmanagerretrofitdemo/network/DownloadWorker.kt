package com.example.workmanagerretrofitdemo.network

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanagerretrofitdemo.utils.Constants.PARAM_MSG
import com.example.workmanagerretrofitdemo.utils.Constants.PARAM_URL
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val outputData: Data
        apiService.getRandomDog().let {
            outputData = if (it.isSuccessful) {
                workDataOf(PARAM_URL to it.body()?.message)
            } else {
                workDataOf(PARAM_MSG to it.errorBody()?.string())
            }
        }
        return Result.success(outputData)
    }

}