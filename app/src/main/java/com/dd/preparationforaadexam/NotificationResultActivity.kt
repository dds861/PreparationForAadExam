package com.dd.preparationforaadexam

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class NotificationResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_result)
        Log.i("autolog", "setContentView: ");

        // When launched from an addAction Intent, we must manually cancel
        // the notification otherwise it will stay in the status bar
        val intent = intent
        val notifyID = intent.getIntExtra("notifyID", 0)

        val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.cancel(notifyID)
    }
}
