package com.example.workmanagerretrofitdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.bumptech.glide.Glide
import com.example.workmanagerretrofitdemo.databinding.ActivityMainBinding
import com.example.workmanagerretrofitdemo.network.DownloadWorker
import com.example.workmanagerretrofitdemo.utils.Constants.PARAM_MSG
import com.example.workmanagerretrofitdemo.utils.Constants.PARAM_URL
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.button.setOnClickListener {
            createWorkRequest()
        }

        setContentView(binding.root)
    }

    private fun createWorkRequest() {
        val workRequest: WorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(workRequest.id).observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    setImage(it.outputData.getString(PARAM_URL) ?: "")
                } else {
                    setError(it.outputData.getString(PARAM_MSG) ?: "")
                }
            }
    }

    private fun setImage(uri: String) {
        Glide.with(this).load(uri)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)
    }

    private fun setError(msg: String) {
        binding.errTextView.text = msg
    }

}