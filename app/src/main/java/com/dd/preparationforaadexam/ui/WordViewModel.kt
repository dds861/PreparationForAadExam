package com.dd.preparationforaadexam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dd.preparationforaadexam.data.Word
import com.dd.preparationforaadexam.data.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordViewModel(
        private val wordRepository: WordRepository
) : ViewModel() {

    val words: Flow<PagingData<Word>> = wordRepository.allWords.cachedIn(viewModelScope)

    fun getWord(id: Long): LiveData<Word> {
        return wordRepository.getWord(id).asLiveData()
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        wordRepository.insert(word)
    }
}