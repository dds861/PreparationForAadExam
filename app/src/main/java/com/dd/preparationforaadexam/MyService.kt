package com.dd.preparationforaadexam

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

//0. Implement from Service()
class MyService : Service() {

    //2. Create from inner class the variable
    private val binder = MyServiceBinder()

    //3. Override onBind, it must return Binder object
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    //4. Create custom function that will do something
    fun startAction() {
        Log.i("autolog", "Starting Action ")
    }

    //1. Create inner class
    inner class MyServiceBinder : Binder() {
        fun getService() = this@MyService
    }
}