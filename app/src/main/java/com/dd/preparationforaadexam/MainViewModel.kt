package com.dd.preparationforaadexam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.nio.charset.Charset

//implement ViewModel object
class MainViewModel: ViewModel() {

    //Create MutableLiveData object
    val myData = MutableLiveData<String>()

    //Set value to myData variable inside viewModelScope
    fun doWork() {
        viewModelScope.launch {
            myData.value = getText()
        }
    }

    //Get any data from IO
    private suspend fun getText(): String {
        return withContext(Dispatchers.IO) {
            return@withContext "Text from IO"
        }
    }
}