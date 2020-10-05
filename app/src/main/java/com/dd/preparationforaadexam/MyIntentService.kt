package com.dd.preparationforaadexam

import android.app.IntentService
import android.content.Intent
import org.greenrobot.eventbus.EventBus

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        val event = MessageEvent("from the service")
        EventBus.getDefault().post(event)
    }
}