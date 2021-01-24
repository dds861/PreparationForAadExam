package com.dd.preparationforaadexam.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)

    @Query("SELECT * FROM myTable ORDER BY _id")
    fun getList(): DataSource.Factory<Int, Word>

    @Query("SELECT * FROM myTable WHERE _id= :id")
    fun getWord(id: Long): Flow<Word>

    @Query("DELETE FROM myTable")
    suspend fun deleteAll()
}