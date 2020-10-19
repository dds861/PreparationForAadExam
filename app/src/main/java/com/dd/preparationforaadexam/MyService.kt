package com.dd.preparationforaadexam

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

//1. Create 2 constants
const val NOTIFICATION_ACTION_PLAY = "action_play"
const val NOTIFICATION_ACTION_STOP = "action_stop"

class MyService : Service() {

    private val binder = MyServiceBinder()
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //5. Read the action from the Pending Intent and do the action according to Constant Keys
        when (intent?.action) {
            NOTIFICATION_ACTION_PLAY -> startAudio(applicationContext)
            NOTIFICATION_ACTION_STOP -> stopAudio()
        }
        return START_STICKY
    }

    inner class MyServiceBinder : Binder() {fun getService() = this@MyService}

    fun startAudio(context: Context) {
        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        player = MediaPlayer().also {
            it.setDataSource(context, soundUri)
            it.prepare();
            it.start();
        }

        displayForegroundNotification()
    }

    fun stopAudio() {
        player.stop()

        stopForeground(false)
    }

    private fun displayForegroundNotification() {

        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            ""
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, notificationIntent, 0)

        //3. Create 2 PendingIntents for play and stop audio
        val playIntent = getPendingIntent(NOTIFICATION_ACTION_PLAY)
        val stopIntent = getPendingIntent(NOTIFICATION_ACTION_STOP)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Playing music")
            .setContentText("any Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .addAction(0, "Play", playIntent) //4. Add 2 PendingIntents as actions to Notification
            .addAction(0, "Stop", stopIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(0)
            .build()

        startForeground(1001, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "my_service"
        val channelName = "Music Service"

        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        return channelId
    }

    //2. Create function that will get PendingIntent
    private fun getPendingIntent(action: String): PendingIntent {
        val serviceIntent = Intent(this, MyService::class.java).also {
            it.action = action
        }
        return PendingIntent.getService(this, 0, serviceIntent, 0)
    }
}