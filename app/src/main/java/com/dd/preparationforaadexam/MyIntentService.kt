package com.dd.preparationforaadexam

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.core.app.JobIntentService

private const val ACTION_FOO = "com.dd.preparationforaadexam.action.FOO"
private const val EXTRA_PARAM1 = "com.dd.preparationforaadexam.extra.PARAM1"
const val JOB_ID = 1001

//0. Create constant
const val FILE_CONTENTS_KEY = "file_contents_key"
const val RECEIVER_KEY = "receiver_key"

class MyIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        when (intent.action) {
            ACTION_FOO -> {

                //3. get the content from intent
                val content = intent.getStringExtra(EXTRA_PARAM1)

                //4. Create Bundle, put data inside
                val bundle = Bundle()
                bundle.putString(FILE_CONTENTS_KEY, content)

                //5. Create ResultReceiver with key, and send bundle back to receiver
                val receiver = intent.getParcelableExtra<ResultReceiver>(RECEIVER_KEY)
                receiver?.send(Activity.RESULT_OK, bundle)
            }
        }
    }

    companion object {
        @JvmStatic
        //1. Add 3rd parameter to the function
        fun startActionFoo(context: Context, param1: String, receiver: MainActivity.MyResultReceiver) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)

                //2. add "receiver" to Extra
               putExtra(RECEIVER_KEY, receiver)
            }
            enqueueWork(context, MyIntentService::class.java, JOB_ID, intent)
        }
    }
}
