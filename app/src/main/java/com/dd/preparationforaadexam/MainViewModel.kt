package com.dd.preparationforaadexam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//implement ViewModel object
class MainViewModel : ViewModel() {

    //Create MutableLiveData object
    val myData = MutableLiveData<String>()

    //Create Job object
    private lateinit var job: Job

    //Set value to myData variable inside viewModelScope
    fun doWork() {
        //viewModelScope.launch wil return Job object
        job = viewModelScope.launch {
            myData.value = getText()
        }
    }

    //Get any data from IO
    private suspend fun getText(): String {
        return withContext(Dispatchers.IO) {
            return@withContext "Text from IO"
        }
    }

    //Function that Cancels the Job
    fun cancelJob() {
        try {
            //Here we can cancel The Job
            job.cancel()
        } catch (ignore: UninitializedPropertyAccessException) {
        }
    }
}
