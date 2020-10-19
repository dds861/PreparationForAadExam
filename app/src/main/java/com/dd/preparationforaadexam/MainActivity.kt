package com.dd.preparationforaadexam

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //1. Create variable instance of MyService
    private lateinit var myService: MyService

    //2. Create ServiceConnection that will connect the Service
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("autolog", "Service connecting")

            //3. Initialize MyService variable
            val binder = service as MyService.MyServiceBinder
            myService = binder.getService()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick.setOnClickListener {

            //6. Call function from the service
            myService.startAction()
        }
    }


    override fun onStart() {
        super.onStart()

        //4. Start the service using bindService(...) function
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        //5. Unbind Service
        unbindService(connection)
    }
}