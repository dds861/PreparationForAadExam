package com.dd.preparationforaadexam

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

const val NOTIFICATION_ACTION_PLAY = "action_play"
const val NOTIFICATION_ACTION_STOP = "action_stop"

class MyService : Service() {

    private val binder = MyServiceBinder()
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

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

        val playIntent = getPendingIntent(NOTIFICATION_ACTION_PLAY)
        val stopIntent = getPendingIntent(NOTIFICATION_ACTION_STOP)

        //1. Create Bitmap object
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Playing music")
            .setContentText("any Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .addAction(0, "Play", playIntent)
            .addAction(0, "Stop", stopIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(0)
            .setStyle(//2. Create style
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)//3. Add Image
                    .setSummaryText("someText")//4. Add Summary Text
            )
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

    private fun getPendingIntent(action: String): PendingIntent {
        val serviceIntent = Intent(this, MyService::class.java).also {
            it.action = action
        }
        return PendingIntent.getService(this, 0, serviceIntent, 0)
    }
}