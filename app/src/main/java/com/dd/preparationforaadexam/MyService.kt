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

class MyService : Service() {

    private val binder = MyServiceBinder()
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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

        //3. Show the notification
        displayForegroundNotification()
    }

    fun stopAudio() {
        player.stop()

        //4. Stop the notification
        stopForeground(false)
    }

    //2. Display Notification
    private fun displayForegroundNotification() {

        //2.1. Check the platform version
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            ""
        }

        //2.2. Create PendingIntent
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, notificationIntent, 0)

        //2.3. Create Notification
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Playing music")
            .setContentText("any Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(0)
            .build()

        //2.4. Start the notification
        startForeground(1001, notification)

    }

    //1. Create Notification channel
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "my_service"
        val channelName = "Music Service"

        //1.2. Create NotificationChannel
        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

        //1.3. Create NotificationManager
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        return channelId
    }
}