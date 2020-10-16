package com.dd.preparationforaadexam

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

//1. Create key constant
const val DATA_KEY = "data_key"

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {

        //2. Retrieve Data and send it UI using key
        val result = workDataOf(DATA_KEY to "Text from Worker")
        return Result.success(result)
    }
}