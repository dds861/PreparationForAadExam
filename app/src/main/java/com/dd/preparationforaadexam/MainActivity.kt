package com.dd.preparationforaadexam

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    //Create MainViewModel variable
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the viewModel variable
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Start observe the changes of myData variable in the viewModel object
        viewModel.myData.observe(this, Observer {
            Log.i("autolog", it)
        })

        //Launch the function which will do some work
        viewModel.doWork()
    }
}