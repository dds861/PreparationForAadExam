package com.dd.preparationforaadexam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//1. Create class with "ViewModel()" extension
class MainViewModel : ViewModel() {

    //2. Create variable that will be observed
    var data: MutableLiveData<String> = MutableLiveData()
}

