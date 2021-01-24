package com.dd.preparationforaadexam.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<PagedList<Word>> = wordDao.getList().toLiveData(pageSize = 5)

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun getWord(id: Long): Flow<Word> {
        return wordDao.getWord(id)
    }
}