package com.dd.preparationforaadexam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //2. Create Object of Broadcast receiver class
    private lateinit var br: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //3. Instantiate the Object of Broadcast receiver class
        br = MyBroadcastReceiver()

        //4. Create IntentFilter
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        //5. Add Action of airplane mode change
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        //6. Register the Receiver
        registerReceiver(br, filter)
    }

    override fun onDestroy() {

        //7. Unregister it in onDestroy method
        unregisterReceiver(br)
        super.onDestroy()
    }

    //1. Create Broadcast receiver class
    internal class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.i("autolog", "intent.action: " + intent.action);
        }
    }
}