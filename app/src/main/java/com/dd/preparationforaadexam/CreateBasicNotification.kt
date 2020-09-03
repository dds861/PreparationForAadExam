package com.dd.preparationforaadexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.create_basic_notification.*

class CreateBasicNotification : AppCompatActivity() {
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private lateinit var mNotifyManager: NotificationManager
    private val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_basic_notification)

        notify.setOnClickListener {
            sendNotification()
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Mascot Notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private fun sendNotification() {
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder!!.build())
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder? {
        val notificationIntent = Intent(this, CreateBasicNotification::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(notificationPendingIntent) //to set the content intent
            .setAutoCancel(true) //closes the notification when user taps on it.
            .setPriority(NotificationCompat.PRIORITY_HIGH) //set the priority of the notification to HIGH by adding the following line to the notification builder object:
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Set the sound, vibration, and LED-color pattern for your notification (if the user's device has an LED indicator) to the default values.
    }

}