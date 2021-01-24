package com.dd.preparationforaadexam.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dd.preparationforaadexam.data.WordRepository

class WordViewModelFactory(private val wordRepository: WordRepository) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WordViewModel::class.java))
            WordViewModel(wordRepository) as T
        else super.create(modelClass)
    }
}