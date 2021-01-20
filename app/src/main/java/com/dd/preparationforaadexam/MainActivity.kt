package com.dd.preparationforaadexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dd.preparationforaadexam.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    //1. Create MainViewModelFactory variable
    private lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2. Instantiate MainViewModelFactory with random text
        viewModelFactory = MainViewModelFactory("Starting text")

        //3. set "viewModelFactory" to ViewModelProvider
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.data.observe(this, Observer {
            binding.tvText.text = it

        })

        binding.btnSetRandomText.setOnClickListener {
            viewModel.data.postValue("text")

        }
    }
}