package com.dd.preparationforaadexam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//1. Create parametrized constructor
class MainViewModel(s: String) : ViewModel() {

    var data: MutableLiveData<String> = MutableLiveData()

    //2. Initialize "data" variable
    init {
        data.postValue(s)
    }
}

