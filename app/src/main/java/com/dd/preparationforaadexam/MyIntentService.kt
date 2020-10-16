package com.dd.preparationforaadexam

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

private const val ACTION_FOO = "com.dd.preparationforaadexam.action.FOO"
private const val EXTRA_PARAM1 = "com.dd.preparationforaadexam.extra.PARAM1"

//1. Create constant
const val JOB_ID = 1001

//0. Implement JobIntentService
class MyIntentService : JobIntentService() {

    //2. Change from onHandleIntent(...) to onHandleWork(...)
    override fun onHandleWork(intent: Intent) {
        when (intent.action) {
            ACTION_FOO -> {
                Log.i("autolog", intent.getStringExtra(EXTRA_PARAM1))
            }
        }
    }

    companion object {
        @JvmStatic
        fun startActionFoo(context: Context, param1: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
            }
            //4. Instead of context.startService(...) -> put enqueueWork(...)
            enqueueWork(context, MyIntentService::class.java, JOB_ID, intent)
        }
    }
}
