package com.dd.preparationforaadexam

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Binder
import android.os.IBinder

class MyService : Service() {

    private val binder = MyServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    inner class MyServiceBinder : Binder() {
        fun getService() = this@MyService
    }

    //1. Create MediaPlayer object
    private lateinit var player: MediaPlayer

    //2. Create custom function that will Play app's ringtone
    fun startAudio(context: Context) {
        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        player = MediaPlayer().also {
            it.setDataSource(context, soundUri)
            it.prepare();
            it.start();
        }

    }

    //3. Create custom function that will Stop app's ringtone
    fun stopAudio() {
        player.stop()
    }
}