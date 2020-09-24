package com.dd.preparationforaadexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        createNotificationChannel()
        buttonNotification.setOnClickListener { createNotification() }


    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFY_CHANNEL, "Notifications", importance)
            channel.description = "This is a notification channel"

            // Register the channel with the system
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun createNotification() {
        // create the NotificationCompat Builder
        val builder = NotificationCompat.Builder(this, NOTIFY_CHANNEL)

        // Create the intent that will start the ResultActivity when the user
        // taps the notification or chooses an action button
        val intent = Intent(this, NotificationResultActivity::class.java)
        // Store the notification ID so we can cancel it later in the ResultActivity
        intent.putExtra("notifyID", NOTIFY_ID)
        val pendingIntent =
            PendingIntent.getActivity(this, NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        // Set the three required items all notifications must have
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle("Sample Notification")
        builder.setContentText("This is a sample notification")

        // Set the notification to cancel when the user taps on it
        builder.setAutoCancel(true)

        // Set the large icon to be our app's launcher icon
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))

        // Set the small subtext message
        builder.setSubText("Tap to view")

        // Set the content intent to launch our result activity
        builder.setContentIntent(pendingIntent)

        // Add an expanded layout to the notification
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle("This is a big notification")
        bigTextStyle.bigText(resources.getString(R.string.app_name))
        builder.setStyle(bigTextStyle)

        // Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action
        builder.addAction(R.mipmap.ic_launcher, "Action 1", pendingIntent)
        builder.addAction(R.mipmap.ic_launcher, "Action 2", pendingIntent)

        // Set the lock screen visibility of the notification
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // Build the finished notification and then display it to the user
        val notification = builder.build()
        val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.notify(NOTIFY_ID, notification)
    }

    companion object {
        private val NOTIFY_ID = 1001
        private val NOTIFY_CHANNEL = "MY_CHANNEL"
    }
}