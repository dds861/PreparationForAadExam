package com.dd.preparationforaadexam

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

private const val ACTION_FOO = "com.dd.preparationforaadexam.action.FOO"
private const val EXTRA_PARAM1 = "com.dd.preparationforaadexam.extra.PARAM1"

//0. Implement IntentService
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        //6. Find Action
        when (intent?.action) {
            ACTION_FOO -> {
                //7. get String from intent by key
                Log.i("autolog", intent.getStringExtra(EXTRA_PARAM1))
            }
        }
    }

    companion object {

        //1. Create static functions that will start the Service
        @JvmStatic
        fun startActionFoo(context: Context, param1: String) {

            //2. Create Intent object
            val intent = Intent(context, MyIntentService::class.java).apply {

                //3. set action with key
                action = ACTION_FOO

                //4. put extra data
                putExtra(EXTRA_PARAM1, param1)
            }

            //5. Start the service
            context.startService(intent)
        }
    }
}
