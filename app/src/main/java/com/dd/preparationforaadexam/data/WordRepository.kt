package com.dd.preparationforaadexam.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<PagingData<Word>> = Pager(
        config = PagingConfig(pageSize = 5, enablePlaceholders = false, prefetchDistance = 7),
        pagingSourceFactory = { wordDao.getList() }
    ).flow

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun getWord(id: Long): Flow<Word> {
        return wordDao.getWord(id)
    }
}