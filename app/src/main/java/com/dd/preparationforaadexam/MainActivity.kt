package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dd.preparationforaadexam.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //1. Create Binding variable
    private lateinit var binding: ActivityMainBinding

    //4. Create MainViewModel variable
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2. inflate binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        //3. set binding.root to ContentView
        setContentView(binding.root)

        //5. Instantiate viewModel variable
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //6. Start observing "data" variable in MainViewModel
        viewModel.data.observe(this, Observer {

            //7. Set data to TextView
            binding.tvText.text = it

        })

        //4. setOnClickListener on button
        binding.btnSetRandomText.setOnClickListener {

            //8. set String value to "data" variable in MainViewModel
            viewModel.data.postValue("text")

        }
    }
}