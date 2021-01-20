package com.dd.preparationforaadexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//1. Extend class with ViewModelProvider.NewInstanceFactory()
class MainViewModelFactory(private val s: String) : ViewModelProvider.NewInstanceFactory() {

    //2. Override create(...) function
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        //3. Check if the "modelClass" is instance of MainViewModel class
        // return MainViewModel(...) with parameter
        //else do whatever
        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(s) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}