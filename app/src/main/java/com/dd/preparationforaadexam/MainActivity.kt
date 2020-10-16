package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick.setOnClickListener {

            //The application will wait when the event of Network Connection will be available.
            //Upon the satisfaction of this condition WorkManager will continue the work
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            //Add "constraints"
            val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(applicationContext).enqueue(workRequest)
        }
    }
}