package com.dd.preparationforaadexam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Register EventBus
        EventBus.getDefault().register(this)

        btnStart.setOnClickListener {
            //4. start service to send event
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }

    override fun onDestroy() {
        //2. Unregister EventBus
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    //3. Receive Event from EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        Log.i("autolog", "event.message: " + event.message);
    }
}