package com.dd.preparationforaadexam

import android.app.AlarmManager
import android.app.PendingIntent
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
            //4. Create Intent
            val intent = Intent(this, MyReceiver::class.java)
            intent.putExtra("payload", "From the original intent")

            //5. Create PendingIntent
            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext, 1, intent, 0
            )

            //6. Create AlarmManager with 3 seconds delay
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000] = pendingIntent
            Log.i("autolog", "Alarm set in 3 seconds: ")
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