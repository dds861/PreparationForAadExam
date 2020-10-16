package com.dd.preparationforaadexam

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.URL
import java.nio.charset.Charset

//1. Create key constant "MESSAGE_KEY"
const val MESSAGE_KEY = "message_key"
const val DATA_KEY = "data_key"

//2. Change to CoroutineWorker
class MyWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    //3. Add "suspend"
    override suspend fun doWork(): Result {

        //4. Creates "Progresses"
        val data = withContext(Dispatchers.IO) {
            setProgress(workDataOf(MESSAGE_KEY to "Task#1"))
            delay(1000)
            setProgress(workDataOf(MESSAGE_KEY to "Task#2"))
            delay(1000)
            setProgress(workDataOf(MESSAGE_KEY to "Task#3"))
            delay(1000)
            return@withContext "Tasks finished"
        }

        //5. Return "data"
        val result = workDataOf(DATA_KEY to data)
        return Result.success(result)
    }
}