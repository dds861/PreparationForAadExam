package com.dd.preparationforaadexam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.greenrobot.eventbus.EventBus

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("payload")
        EventBus.getDefault().post(MessageEvent("Received message: $message"))
    }
}
