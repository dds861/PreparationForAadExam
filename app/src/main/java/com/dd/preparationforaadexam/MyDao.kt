package com.dd.preparationforaadexam

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDao {

    @Query("SELECT * from my_table_name")
    fun getAll(): List<MyDataClass>

    @Insert
    suspend fun insertMyDataClass(myDataClass: MyDataClass)

    @Insert
    suspend fun insertMyDataClass(myDataClass: List<MyDataClass>)

    @Query("DELETE from my_table_name")
    suspend fun deleteAll()

}