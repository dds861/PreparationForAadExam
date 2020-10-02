package com.dd.preparationforaadexam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //2. Extract data that is being sent from the service
    var mLocalReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.getStringExtra(
                MyIntentService.MESSAGE_KEY
            )
            Log.i("autolog", "Received: $message: ");
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //3. Register the receiver
        LocalBroadcastManager.getInstance(applicationContext)
            .registerReceiver(mLocalReceiver, IntentFilter("custom-event"))

        btnStart.setOnClickListener {
            //1. start service
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }

    override fun onDestroy() {

        //4. Unregister broadcastReceiver in onDestroy method
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(mLocalReceiver)
        super.onDestroy()
    }
}