package com.dd.preparationforaadexam

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var myService: MyService

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {}

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyServiceBinder
            myService = binder.getService()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener { myService.startAudio(this) }
        btnStop.setOnClickListener { myService.stopAudio() }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

}