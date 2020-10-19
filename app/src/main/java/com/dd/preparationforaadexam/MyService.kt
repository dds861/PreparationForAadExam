package com.dd.preparationforaadexam

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val binder = MyServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    //The system invokes this method by calling startService() in MainActivity(...)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    fun startAction() {
        Log.i("autolog", "Starting Action ")
    }

    inner class MyServiceBinder : Binder() {
        fun getService() = this@MyService
    }
}