package com.dd.preparationforaadexam

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        Log.i("autolog", "onHandleIntent: ");

        //Return from service to application
        val returnIntent = Intent("custom-event")
        returnIntent.putExtra(MESSAGE_KEY, "from the service")

        //As the service completes it will send the message
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(returnIntent)
    }

    companion object {
        const val MESSAGE_KEY = "message_key"
    }
}