package com.dd.preparationforaadexam.data

import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getList()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun getWord(id: Long): Flow<Word> {
        return wordDao.getWord(id)
    }
}