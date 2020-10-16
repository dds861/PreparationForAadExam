package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

btnClick.setOnClickListener {

    val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
    val workManager = WorkManager.getInstance(applicationContext)
    workManager.enqueue(workRequest)

    workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, Observer {
        tvText.text = when (it.state) {
            WorkInfo.State.SUCCEEDED -> {
                it.outputData.getString(DATA_KEY)
            }

            //1. Add State item and set it's value to TextView
            WorkInfo.State.RUNNING -> {
                it.progress.getString(MESSAGE_KEY)
            }
            else -> "Empty"
        }
    })
}
    }
}